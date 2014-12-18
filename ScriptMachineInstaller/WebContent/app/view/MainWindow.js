Ext.define('SMI.view.MainWindow', {
	extend : 'Ext.window.Window',
	alias : 'widget.mainwindow',
	id: 'mainwindow',
	renderTo: 'smi_main',
	header: false,
	autoWidth : true,
	width: '100%',
	height : '100%',
    layout: 'fit',
	header: false,
    resizable: false,
    maximized : true,
    closable: false,
    padding: 0,
    closable: false,
    border: 0,
    style: {
    	border: 0
    },
    titleCollapse: false,
    userClosable : false,
    minHeight : 500,
    
	initComponent : function() {
		var me = this;
		me.callParent(arguments);
	}
});


