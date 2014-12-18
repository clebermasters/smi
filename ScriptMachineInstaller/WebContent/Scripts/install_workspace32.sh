#/
# * @author cleber_rodrigues
# * 
# */


#!/bin/bash


# Set to false to not install the applications, changing the variables below:

### DEFINE FLAG TO INSTALL
	jdk17Install=true
	chromeInstall=true
	skypeInstall=true
	guakeInstall=true
	virtualbox=true
	PgAdmin3Install=true
	geanyInstall=true
### DEFINE FLAG TO INSTALL-END

### DEFAULT FLAGS
	configureProxy=true
### DEFAULT FLAGS-END

### FOLDER DEFINITION
	jdkSourceFile="jdk-7u65-linux-i586.tar.gz"
	jdkFolder="jdk1.7"
	jvmPath="/usr/lib/jvm"
	jreSourceFile="jre-7u65-linux-i586.tar.gz"
	jreFolder="jre1.7"
	pwdOriginal="`pwd`"
### FOLDER DEFINITION-END

### APP DEFINITION
	7zip="7z920.tar.bz2"
	virtualBoxFile="virtualbox-4.3_4.3.12-93733~Ubuntu~raring_i386.deb"
	chromeDEB="google-chrome-stable_current_i386.deb"
	nvidiaDrive="NVIDIA-Solaris-x86-319.17.run"
### APP DEFINITION-END

### CONFIG FILES DEFINITION
	token_file="token.jnlp"
	maven_setting="settings.xml"
	apt="apt.conf"
	plugin_java="libnpjp2.so"
### CONFIG FILES DEFINITION-END


### SCRIPT DOWNLOADER


### INIT COMMANDS
	#Give permission for all files.
	sudo chmod 777 -R $pwdOriginal

	# Create alls folders
	sudo mkdir /logs
	sudo mkdir $jvmPath
	sudo mkdir -p /opt/google/chrome/plugins
	sudo mkdir ~/.m2
### INIT COMMANDS-END


### PROXY CONFIGURATION
	if $configureProxy = true ; then
		# Configure proxy
		> $apt
		echo "Type your global password: " ; read password
		echo 'Acquire::http::Proxy "http://'$USER':'$password'@143.116.92.201:64000";' >> $apt
		sudo cp $apt /etc/apt/
		> $apt
		gsettings set org.gnome.system.proxy mode 'auto'
	fi
### PROXY CONFIGURATION-END

### UPTADE APT-GET REPOSITORY
	if $skypeInstall = true ; then
		sudo add-apt-repository "deb http://archive.canonical.com/ $(lsb_release -sc) partner"
	fi 
### UPTADE APT-GET REPOSITORY-END


# Update packages
sudo apt-get update 


### APT-GET PACKAGES

	if $skypeInstall = true ; then
		# Install Skype
		sudo apt-get -y install skype
	fi

	if $PgAdmin3Install = true ; then
		# Install PgAdmin3
		sudo apt-get -y install pgadmin3
	fi

	if $guakeInstall = true ; then
		# Install Guake Termial
		sudo apt-get -y install guake
	fi 

	if $geanyInstall = true ; then 
		# Install Geany (Notepad++ Alternative)
		sudo apt-get -y install geany
	fi 

	if $chromeInstall = true ; then
		# Download and Install Google Chrome
		sudo apt-get -y install libappindicator1
		sudo wget https://dl.google.com/linux/direct/google-chrome-stable_current_amd64.deb
		sudo dpkg -i google-chrome-stable_current_amd64.deb
	fi 

### APT-GET PACKAGES-END


### MAIN JAVA INSTALL

	if $jdk17Install = true ; then

		# Copy all files
		cd $pwdOriginal
		sudo cp $jdkSourceFile $jvmPath
		sudo cp $jreSourceFile $jvmPath

		# Extract the Java Packages
		cd $jvmPath
		sudo chmod 777 -R $jvmPath
		sudo tar -zxvf $jdkSourceFile
		sudo tar -zxvf $jreSourceFile

		# Rename folders
		sudo mv jdk1.7.0_65 $jdkFolder
		sudo mv jre1.7.0_65 $jreFolder

		# Give permissions

		sudo chmod 777 -R $$jvmPath

		# Install Java
		sudo update-alternatives --install /usr/bin/javac javac $jvmPath/$jdkFolder/bin/javac 1
		sudo update-alternatives --install /usr/bin/java java $jvmPath/$jdkFolder/bin/java 1
		sudo update-alternatives --install /usr/bin/javaws javaws $jvmPath/$jdkFolder/bin/javaws 1

		# Install Java Plugin
		cd /usr/lib/firefox-addons/plugins/
		sudo ln -s $jvmPath/$jreFolder/lib/i386/$plugin_java
		cd /opt/google/chrome/plugins
		sudo ln -s $jvmPath/$jreFolder/lib/i386/$plugin_java

		# Install Environment Variables
		export JAVA_HOME=/usr/lib/jvm/jdk1.7
		export PATH=$PATH:$JAVA_HOME/bin

		xdg-mime install $jvmPath/$jreFolder/lib/desktop/mime/packages/x-java-jnlp-file.xml

		# Associate jnlp files
		cd $pwdOriginal
		cp mimeapps.list ~/.local/share/applications

	fi

### MAIN JAVA INSTALL-END


### FILE INSTALL

	###virtualbox
		if $virtualbox = true ; then
			# Install VirtualBox
			sudo dpkg --configure -a
			sudo apt-get -y install libsdl1.2debian
			sudo dpkg -i $virtualBoxFile
			sudo /etc/init.d/vboxdrv setup
		fi
	###virtualbox-END

### FILE INSTALL-END

### GENERAL CONFIGURATION FILES

	# Copy profile with environment variables
	cd $pwdOriginal
	sudo cp bashrc ~/.bashrc

	sudo cp $maven_setting ~/.m2/
	sudo chmod 777 -R ~/.m2/

	#Create a link for Eclipse
	cd ~/Desktop
	ln -s /usr/dev/tool-dev/ide/eclipse/eclipse

	# Copy Token to Desktop
	cd $pwdOriginal
	sudo cp $token_file ~/Desktop

### GENERAL CONFIGURATION FILES-END

