# DevOps for Tarot
## List of notable software on Tarot sever:
1. Apache HTTP Server
  * Allows access to the Tarot website (login) through a web browser.
  * Config files are at /etc/apache2/ports.conf and /etc/apache2/sites-enabled/000-default.conf
  * Port 80 is reverse-proxied to connect to port 8080, which hosts a Docker container with the Tarot website.
  * Port 8888 hosts administrative web apps like Munin and GitStats.
2. GitStats
  * Used for tracking commit history and producing statistics for the Tarot repository.
  * Located at /var/www/gitstats
3. Munin
  * Used for monitoring resources and logging activities on the server.
  * Located at /var/www/munin
4. Travis-CI
  * Continuous integration tool used for running automatic tests on Tarot code when it gets updated.
  * Runs a test every time someone commits to the Tarot repository.
5. Docker
  * Used to run the container for the Tarot website
  * Container listens on port 8080

## How to access GitStats and Munin
For security reasons, GitStats and Munin are not accessible publicly on tarot.artifice.cc . They live on port 8888, which is not visible to the public, and only accessible by the Tarot server itself. However, you can access it with an SSH tunnel during an SSH session. This method ensures that only authorized users can see this information.

### 1. Set up an SSH tunnel for port 8888 on the Tarot server and open a connection
For Windows, use PuTTY with a configuration like this:

Note: make sure to click “Add” when you enter the ports (right image)

![alt text](https://github.com/StetsonMathCS/tarot/blob/master/docs/img/devops_01.png "PuTTY configuration")
### 2. Open a web browser and type “localhost:8888” into the URL field at the top
![alt text](https://github.com/StetsonMathCS/tarot/blob/master/docs/img/devops_02.png "directory index")

Note: you can only access this URL while your SSH session is open. Don’t mind any of the .php files or passports/ directory. They are for another project.

### 3. From there, you can click on gitstats/ or munin/ to see them
![alt text](https://github.com/StetsonMathCS/tarot/blob/master/docs/img/devops_03.png "Munin")
***
![alt text](https://github.com/StetsonMathCS/tarot/blob/master/docs/img/devops_04.png "GitStats")

## How to access Travis-CI

Note: you need to be a member of the StetsonMathCS/tarot repository on GitHub to access Travis. If you are not currently a member, please ask the creator of the repository (Dr. Eckroth) for an invite to join, and accept the invite.

### 1. Go to travis-ci.org and sign up for the service with your GitHub account credentials

![alt text](https://github.com/StetsonMathCS/tarot/blob/master/docs/img/devops_05.png "GitStats")

### 2. The tarot repository should show up on the left side under “My Repositories” and you can look at the build history and see current builds being run in real-time.

![alt text](https://github.com/StetsonMathCS/tarot/blob/master/docs/img/devops_06.png "GitStats")

Note: if you don't immediately see the Tarot repository, that means that you either have not accepted the invite to join the repository, or that your GitHub account is not properly configured to send information about organizations you are a part of to Travis.
