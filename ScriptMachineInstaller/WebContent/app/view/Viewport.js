Ext.define('SMI.view.Viewport', {
	extend : 'Ext.container.Container',
	requires : ['SMI.view.mainPanel','SMI.view.AppGrid'],
	alias: 'widget.customviewport',
	renderTo : 'smi_viewport',
	layout : 'fit',
	border : 0,
	padding : 0,
	width : '100%',
	height : '100%',
	title : 'Script Machine Installer',
	id: 'smi_main',
	
	initComponent: function() {
		var me = this;
		me.items = [];
		this.callParent(arguments);
	},
	
	
});