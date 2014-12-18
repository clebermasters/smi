Ext.define('SMI.view.loadBlock', {
	extend: 'Ext.window.Window',
	alias: 'widget.loadBlock',
	id: 'loadBlock',
	height: 110,
	width: 500,
	bodyPadding: 10,
	title: 'Load Blocks',

	initComponent: function() {
    	var me = this;

    	var blockDefinition = Ext.create('Ext.data.Store', {
    	    fields: ['blockName', 'block'],
    	    autoLoad : true,
    	    data : [
					{"blockName":"FLAG TO INSTALL", "block":"FLAGTOINSTALL"},
					{"blockName":"DEFAULT FLAGS", "block":"DEFAULTFLAG"},
					{"blockName":"FOLDER DEFINITION", "block":"FOLDERDEFINITION"},
					{"blockName":"APP DEFINITION", "block":"APPDEFINITION"},
					{"blockName":"CONFIG FILES", "block":"CONFIGFILESDEFINITION"},
					{"blockName":"INIT COMMANDS", "block":"INITCOMMANDS"},
					{"blockName":"PROXY CONFIGURATION", "block":"PROXYCONFIGURATION"},
					{"blockName":"UPDATE APT-GET", "block":"UPDATEAPTGETREPOSITORY"},
					{"blockName":"APT-GET PACKAGES", "block":"APTGETPACKAGES"},
					{"blockName":"MAIN JAVA", "block":"MAINJAVAINSTALL"},
					{"blockName":"FILES INSTALL", "block":"FILEINSTALL"},
					{"blockName":"GENERAL CONFIGURATION FILES", "block":"GENERALCONFIGURATIONFILES"}
    	    ]
    	});

    	Ext.applyIf(me, {
        	layout: {
            	type: 'vbox',
            	align: 'stretch'
        	},
        	items: [
            	{
                	xtype: 'panel',
                	flex: 1,
                	layout: {
                    	type: 'vbox',
                    	align: 'stretch'
                	},
                	items: [
                	        {
                	        	xtype: 'combobox',
                	        	id : 'comboBlocks',
                	        	store: blockDefinition,
                	            queryMode: 'local',
                	            displayField: 'blockName',
                	            valueField: 'block',
                	        
                	
                	
                	        },
                	        {
                	        	xtype : 'button',
                	        	text: 'Load',
                	        	action: 'onLoadBlock'
                	        }
                	 ]
            	}
        	]
    	});

    	me.callParent(arguments);
	}

});
