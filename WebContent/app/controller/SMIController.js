Ext.define('SMI.controller.SMIController', {
	extend : 'Ext.app.Controller',
//	stores : ['SMI.store.agendaStore'],
	views: ['SMI.view.mainPanel','SMI.view.loadBlock'],
//  models: ['SMI.model.agendaModel'], 
	init: function() {
	
		console.log('Controle iniciado');
		
		this.loadScriptInstaller();
		
		this.control({
			'mainpanel button[action=btnSentToServer]' : {
				click : this.onSendToServer
			},
			
			'mainpanel button[action=onDeleteApp]' : {
				click : this.onDeleteApp
			},
			
			'#appcombobox' : {
				select : this.onSelectApp
			},
			
			'mainpanel button[action=onLoadBlockWindow]' : {
				click : this.onLoadBlockWindow
			},
			
			'loadBlock button[action=onLoadBlock]' : {
				click : this.onLoadBlock
			},
			
			'mainpanel button[action=onSaveAppScript]' : {
				click : this.onSaveAppScript
			},
		});

	},
	
	onLoadBlockWindow : function () {
		var loadBlockWindow = Ext.widget('loadBlock');
		loadBlockWindow.show();
	},
	
	onLoadBlock : function () {
		var comboBlocks = Ext.getCmp('comboBlocks');
		var scripteditor = Ext.getCmp('scripteditor');
		
		Ext.Ajax.request({
		    url: 'loadBlock',
		    async: false,
		    method: 'GET',
		    params: {
		        block: comboBlocks.getValue(),
		    },
		    success: function(response){
		    	 var text = Ext.decode(response.responseText).data;
		    	 scripteditor.setValue(text);
		    	 scripteditor.blockLoad = comboBlocks.getValue();
		    	 scripteditor.blockType = 'block';
		    	 Ext.getCmp('loadBlock').close();
		    }
		});
	},
	
	onSelectApp : function (combo, records, eOpts){
		Ext.Ajax.request({
		    url: 'getAppScript',
		    async: false,
		    method: 'GET',
		    params: {
		        app: combo.getValue(),
		    },
		    success: function(response){
		    	var text = Ext.decode(response.responseText).data;
		    	var scripteditor = Ext.getCmp('scripteditor');
		    	scripteditor.setValue(text);
		    	scripteditor.blockLoad = combo.getValue();
		    	scripteditor.blockType = 'app';
		    	
		    }
		});
	},
	
	
	onDeleteApp : function () {
    	var grid = Ext.getCmp('appgrid'),
    		store = grid.getStore(),
			recordSelected = grid.getSelectionModel().getSelection()[0];
    	
		Ext.Ajax.request({
		    url: 'deleteApp',
		    async: false,
		    method: 'GET',
		    params: {
		        fileName: recordSelected.data.fileName,
		        key: recordSelected.data.key,
		    },
		    success: function(response){
		       store.load();
		    }
		});
		
	},
	
	loadScriptInstaller : function (){
		Ext.Ajax.request({
		    url: 'loadScriptInstaller',
		    async: false,
		    method: 'GET',
		    params: {
		        id: 1
		    },
		    success: function(response){
		        var text = Ext.decode(response.responseText).data;
		        var scriptInstaller = Ext.getCmp('scriptInstaller');
		        scriptInstaller.setValue(text);
		    }
		});
	},
	
	loadCombobox : function (){
		var combobox = Ext.getCmp('appcombobox');
	},
	
	start : function ()  {
		var url = Ext.getCmp('url').getValue();
		var key = Ext.getCmp('appcombobox').getValue();
		var me = this;
        var eventSource = new EventSource("sentToServer?url="+ url + "&" + "key=" + key);
        var downloadStatus = Ext.getCmp('downloadStatus');
        downloadStatus.updateText('Starting Download');
        downloadStatus.updateProgress(0/100);
        eventSource.onmessage = function(event) {
        	 var scripteditor = Ext.getCmp('scripteditor');
        	 var downloadStatus = Ext.getCmp('downloadStatus');
        	 if (event.data == 'finished') {
        		this.close();
        		downloadStatus.updateText('Download Finished');
        		SMI.controller.SMIController.prototype.onAfterDownload();
        	} else if (event.data == 'already there') {
        		this.close();
        		downloadStatus.updateText('The file is already exists');
        	} else {
        		var temp = event.data.match('...%');
        		if (temp != null){
        			temp = temp.toString().trim();
        			var value = parseInt(temp.replace('%',''));
        			downloadStatus.updateProgress(value/100);
        			downloadStatus.updateText('Download ' + value + '%');
        		}
        	}
        };
    },
    
    onAfterDownload : function (){
    	var grid = Ext.getCmp('appgrid');
    	var store = grid.getStore();
    	store.load();
    },
	
	
	onSendToServer : function (){
		var northForm = Ext.getCmp('northForm').getForm();
		if (northForm.isValid())
		this.start();
	},
    
    
    onSaveAppScript : function () {
    	var scripteditor = Ext.getCmp('scripteditor');
		Ext.Ajax.request({
		    url: 'updateScript',
		    async: false,
		    method: 'GET',
		    params: {
		        script: scripteditor.getValue(),
		        blockLoad : scripteditor.blockLoad,
		        blockType : scripteditor.blockType
		    },
		    success: function(response){
		    	debugger;
		    }
		});
    	
    	
    }
    
    
});
