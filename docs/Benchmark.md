# Tarot Benchmarking

## Features
* Performance: how long it takes for algorithm to run
* Percentage of failed vs valid web application requests
* Real time statistics displayed on a dashboard
* Active users on application
* Generating log reports
* Converting logs to a visual representation
* Dashboard of static log analytics
* Average users spent on application
* Record of most used/clicked information
* Overall web traffic


## Programs
* Google Analytics
* Plot.ly
* GoAccess
* Bash


## Installation
#### Google Analytics
* Create or sign in to your Analytics account at  google.com/analytics
* Edit your permissions 
* In the ACCOUNT column, use the menu to select the account to which you want to add the property
* In the PROPERTY column, select Create new property from the menu
* Select Website
* Enter the Website or App Name
* Enter the Web Site URL
* Click Get Tracking ID
* This will be the Global Site Tag (gtag.js) tracking code. Copy and paste the code as the first item into the <HEAD> of every webpage you want to track. If you already have a Global Site Tag on your page, simply add the config line from the snippet below to your existing Global Site Tag.
* Make sure to setup dashboard to your specifications

#### Plot.ly
* Create account for free access at https://plot.ly/Auth/login/?next=%2Fsettings
* Click the "Create" button to get started
* Select desired project type to work on
* Import any you wish to visualize

#### GoAccess
* Download GoAccess, choose one of the following options:
  * Download and build from source (tar.gz)
  * Use your preferred package manager of your Linux distribution.
 * Build from development and get the latest and greatest.
 * Build GoAccess' Docker image from upstream.


## How to run/view dashboards
### Bash script
* Run the AvgCompTime.sh file to view the average time it takes to run tarot algorithm

>

    sh AvgCompTime.sh


### GoAccess
> * Currently unable to generate logs in HTML, JSON, and CSV since there is no time associated with the logs
* Can only produce static reports
* Our logs have .txt extension 

* Navigate to where the logs exist
* To generate a single log report, run

>

    goaccess <filename.txt>

* To generate a multi log report, run

>

    goaccess <filename1.txt> <filename2.txt>

* To generate a report of all logs, run

>

    goaccess <*.txt>

### Google Analytics
>Must have account to access Google Analytics dashboard

* https://analytics.google.com/analytics/web/#/dashboard/CHvwWfpVQoeD5SyBNdOB_A/a127494221w186432833p183392384/_.useg=builtin1/

### Plot.ly
* https://plot.ly/~spratico/10/tarot-dashboard/
