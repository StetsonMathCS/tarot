import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicLong;

//Tutorial to find files in the computer 
//https://www.youtube.com/watch?v=XXkq73u9Uqg
public class DatabaseAndCSV 
{
	private static final File fil = new File("");
	static String l;
	static String password, username;

	private static class Search implements Runnable 
	{
		private final BlockingQueue<File> queued;
		private final BlockingQueue<File> queuef;
		private final AtomicLong ct;
		private final int number;

		public Search(final BlockingQueue<File> queued, final BlockingQueue<File> queuef, final AtomicLong ct,
				final int number) 
		{
			this.queued = queued;
			this.queuef = queuef;
			this.ct = ct;
			this.number = number;
		}

		public void run() 
		{
			try 
			{
				File dir = queued.take();
				while (dir != fil) 
				{
					final File[] files = dir.listFiles();
					if (files != null) 
					{
						for (final File element : files) 
						{
							if (element.isDirectory()) 
							{
								ct.incrementAndGet();
								queued.put(element);
							} else 
							{
								queuef.put(element);
							}
						}
					}
					final long c = ct.decrementAndGet();
					if (c == 0) 
					{
						end();
					}
					dir = queued.take();
				}
			} catch (final InterruptedException ie) 
			{
			}
		}

		private final void end() 
		{
			try 
			{
				queuef.put(fil);
			} catch (final InterruptedException e) 
			{
			}
			for (int i = 0; i < number; i++) {
				try {
					queued.put(fil);
				} catch (final InterruptedException e) 
				{
				}
			}
		}
	}

	private static class CallableFileSearch implements Callable<File> 
	{
		private final BlockingQueue<File> queued;
		private final BlockingQueue<File> queuef;
		private final String name;
		private final int number;

		public CallableFileSearch(final BlockingQueue<File> queued, final BlockingQueue<File> queuef,
				final String name, final int number) 
		{
			this.queued = queued;
			this.queuef = queuef;
			this.name = name;
			this.number = number;
		}

		public File call() throws Exception 
		{
			File file = queuef.take();
			while (file != fil) 
			{
				final String filename = file.getName().toLowerCase();
				final String lf = name.toLowerCase();
				if (filename.equalsIgnoreCase(name) || filename.startsWith(lf) || filename.endsWith(lf)) 
				{
					end();
					return file;
				}
				file = queuef.take();
			}
			return null;
		}

		private final void end() 
		{
			for (int i = 0; i < number; i++) 
			{
				try {
					queued.put(fil);
				} catch (final InterruptedException e) 
				{
				}
			}
		}
	}

	private final String filename;
	private final File baseD;
	private final int concurrency;
	private final AtomicLong ct;

	public DatabaseAndCSV(final String filename, final File baseD, final int concurrency) 
	{
		this.filename = filename;
		this.baseD = baseD;
		this.concurrency = concurrency;
		ct = new AtomicLong(0);
	}

	public File find() 
	{
		final ExecutorService x = Executors.newFixedThreadPool(concurrency + 1);
		final BlockingQueue<File> queued = new LinkedBlockingQueue<File>();
		final BlockingQueue<File> queuef = new LinkedBlockingQueue<File>(10000);
		for (int i = 0; i < concurrency; i++) 
		{
			x.submit(new Search(queued, queuef, ct, concurrency));
		}
		ct.incrementAndGet();
		queued.add(baseD);
		final Future<File> c = x.submit(new CallableFileSearch(queued, queuef, filename, concurrency));
		try 
		{
			final File f = c.get();
			return f;
		} catch (final Exception e) 
		{
			return null;
		} finally 
		{
			x.shutdownNow();
		}
	}

	/*
	 * Import csv file to database
	 * Export database information to csv file
	 * Put csv file to temporary path
	 */
	public static void main(final String[] args) 
	{
		final String filename = "6c27ff1c-cd10-480f-b730-688f6d2ae56b.csv";
		final File baseDir = new File("C:/");
		final DatabaseAndCSV ff = new DatabaseAndCSV(filename, baseDir, 6);
		File f = ff.find();
		System.out.println(f);
		l = f.toString();

		// The location of the csv file to import information to the database
		String importfilename = l;
		// The location to put the csv file after exporting the table from the database
//    	String exportfilename = "C:\\Users\\Isabel Ho\\Desktop\\schedule_table.csv";
		File file = new File(importfilename);
		Connection connectDatabase = null;
		Statement insertTable = null;
		Statement createTable;
		FileInputStream input_info = null;

		/*
		 * Get password and username information from textfile to connect to the database
		 */
		Properties properties = new Properties();
		try {

			FileInputStream stream = new FileInputStream("input.txt");
			properties.load(stream);

			username = properties.getProperty("user");
			password = properties.getProperty("pwd");

		} catch (IOException ex) 
		{
			ex.printStackTrace();
		} finally {
			if (input_info != null) 
			{
				try 
				{
					input_info.close();
				} catch (IOException e) 
				{
					e.printStackTrace();
				}
			}
		}

		try 
		{
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String line;
			String zero = null, one = null, two = null, three = null, four = null;
			try 
			{
				String url = "jdbc:mysql://localhost:3306/schedule_table";
				Class.forName("com.mysql.jdbc.Driver");
				connectDatabase = DriverManager.getConnection(url, username, password);
				insertTable = connectDatabase.createStatement();
			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			line = buffer.readLine();
			String[] array = line.split(";");
			
			/*
			 * Create table in the database
			 */
			try 
			{
				createTable = connectDatabase.createStatement();
				String create = "create table schedule_table(" + array[0] + " varchar(255)," + array[1] + " int,"
						+ array[2] + " varchar(255)," + array[3] + " varchar(255)," + array[4] + " varchar(255))";

				zero = array[0];
				one = array[1];
				two = array[2];
				three = array[3];
				four = array[4];

				createTable.executeUpdate(create);
				System.out.println("The table was created sucessfully");

			} catch (Exception e) 
			{
				e.printStackTrace();
			}

			/*
			 * Insert information into the table
			 */
			while ((line = buffer.readLine()) != null) 
			{

				array = line.split(";");
				try 
				{
					String insert = "INSERT INTO schedule_table " + "(" + zero + "," + one + "," + two + "," + three
							+ "," + four + ") " + "VALUES ('" + array[0] + "','" + array[1] + "','" + array[2] + "'"
							+ ",'" + array[3] + "','" + array[4] + "') ";
					insertTable.execute(insert);
				} catch (SQLException e) 
				{
					e.printStackTrace();
				}
				System.out.println("The information was imported successfully");
			}
			buffer.close();
		} catch (IOException e) 
		{
			e.printStackTrace();
		}

		/*
		 * Create a temporary file
		 * Get temporary file path
		 */
		try 
		{
			File temporary = File.createTempFile("temporary-file", ".csv");

			System.out.println("Temporary file : " + temporary.getAbsolutePath());

			String path1 = temporary.getAbsolutePath();
			String temporaryPath = path1.substring(0, path1.lastIndexOf(File.separator));

			System.out.println("Temporary file path : " + temporaryPath);
			FileWriter writer = new FileWriter(temporary);
			String select = "select * from schedule_table";
			Statement writeTable = connectDatabase.createStatement();
			ResultSet result = writeTable.executeQuery(select);
			while (result.next()) {
				writer.append(result.getString(1));
				writer.append(',');
				writer.append(result.getString(2));
				writer.append(',');
				writer.append(result.getString(3));
				writer.append(',');
				writer.append(result.getString(4));
				writer.append(',');
				writer.append(result.getString(5));
				writer.append('\n');
			}
			writer.flush();
			writer.close();
			connectDatabase.close();
			System.out.println("CSV File is created successfully.");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}