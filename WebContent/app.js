
Ext.Loader.setPath('SMI', '/ScriptMachineInstaller/app');

Ext.Loader.setConfig({
 	require: ['SMI.view.Viewport'],
	enabled : true,
});

Ext.application({
	name : 'SMI',
	appFolder : '/ScriptMachineInstaller/app',
	controllers: ['SMIController'],
	autoCreateViewport : false, // automatically creates Viewport
    requires: [],
	launch : function() {
	}
});


Ext.onReady(function () {
	var viewport = Ext.create('SMI.view.Viewport');
	var mainwindow = Ext.create('SMI.view.MainWindow');
	
	var controlpanel = Ext.create('SMI.view.mainPanel');
	
	mainwindow.add(controlpanel)
	
	mainwindow.show();
		
	
});
