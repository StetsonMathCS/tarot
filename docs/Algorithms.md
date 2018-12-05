Our program generates university time tables using IBM ILOG CPLEX optimization software.
__To install__:
  * *For Windows*
    * Visit https://www.ibm.com/products/ilog-cplex-optimization-studio Where you can either sign up for a trial version, purchase the program, or sign up for an academic license which provides you full access to CPLEX in a non-commercial setting
    * Download, run the installer as administrator, which will guide you through the process and setup the CPLEX Optimization Studio
    * Default install location is C:\Program Files\IBM\ILOG\CPLEX_Studio[edition]1261 or Program Files(x86) if installed 32-bit version on 64-bit machine
      * See [here](https://www.ibm.com/support/knowledgecenter/SSSA5P_12.6.2/ilog.odms.studio.help/pdf/gsoplide.pdf?origURL=SSSA5P_12.6.2/ilog.odms.studio.help/Optimization_Studio/topics/PLUGINS_ROOT/ilog.odms.studio.help/pdf/gsoplide.pdf) for more information on the Optimation Studio IDE
  * *For Unix/Linux/Mac*
    * Visit same [website](https://www.ibm.com/products/ilog-cplex-optimization-studio) for download
     * The linux/unix installer comes as an executable .bin file, located within the installation folder, default location /opt/IBM/ILOG/CPLEX_Studio[edition]1261. 
     * Run the executable on your system's commandline with the syntax __./(installname).bin__ make sure the file has execute permissions or installation will fail
      * Launch CPLEX using __./cplex__ located in /opt/IBM/ILOG/CPLEX_Studio[edition]1261/cplex/bin/x86-64_linux/
