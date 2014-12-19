Ext.define('SMI.view.testWindow', {
	extend: 'Ext.window.Window',

	requires: [
    	'Ext.form.FieldSet',
    	'Ext.form.field.Text',
    	'Ext.form.field.Checkbox',
    	'Ext.form.field.Display',
    	'Ext.button.Button',
    	'Ext.grid.Panel',
    	'Ext.grid.column.Column',
    	'Ext.grid.View'
	],

	height: 575,
	width: 655,
	bodyPadding: 10,
	title: 'My Window',

	initComponent: function() {
    	var me = this;

    	Ext.applyIf(me, {
        	layout: {
            	type: 'vbox',
            	align: 'stretch'
        	},
        	items: [
            	{
                	xtype: 'panel',
                	flex: 1,
                	header: false,
                	title: 'My Panel',
                	layout: {
                    	type: 'vbox',
                    	align: 'stretch'
                	},
                	items: [
                    	{
                        	xtype: 'fieldset',
                        	flex: 0.5,
                        	height: 295,
                        	title: 'Part Number Detail',
                        	layout: {
                            	type: 'vbox',
                            	align: 'stretch'
                        	},
                        	items: [
                            	{
                                	xtype: 'textfield',
                                	fieldLabel: 'Part Number'
                            	},
                            	{
                                	xtype: 'textfield',
                                	fieldLabel: 'Description'
                            	},
                            	{
                                	xtype: 'textfield',
                                	fieldLabel: 'Component Mask'
                            	},
                            	{
                                	xtype: 'checkboxfield',
                                	fieldLabel: 'Serialized',
                                	boxLabel: ''
                            	},
                            	{
                                	xtype: 'panel',
                                	header: false,
                                	title: 'My Panel',
                                	layout: {
                                    	type: 'hbox',
                                    	align: 'stretch'
                                	},
                                	items: [
                                    	{
                                        	xtype: 'displayfield',
                                        	flex: 1,
                                        	fieldLabel: 'Quantity',
                                        	value: 'Display Field'
                                    	},
                                    	{
                                        	xtype: 'displayfield',
                                        	flex: 1,
                                        	fieldLabel: 'Scanned',
                                        	value: 'Display Field'
                                    	}
                                	]
                            	},
                            	{
                                	xtype: 'textfield',
                                	fieldLabel: 'Serial Number'
                            	},
                            	{
                                	xtype: 'panel',
                                	header: false,
                                	title: 'My Panel',
                                	layout: {
                                    	type: 'hbox',
                                    	align: 'middle',
                                    	pack: 'center'
                                	},
                                	items: [
                                    	{
                                        	xtype: 'button',
                                        	text: 'MyButton'
                                    	}
                                	]
                            	}
                        	]
                    	}
                	]
            	},
            	{
                	xtype: 'panel',
                	flex: 1,
                	header: false,
                	manageHeight: false,
                	title: 'My Panel',
                	layout: {
                    	type: 'vbox',
                    	align: 'stretch'
                	},
                	items: [
                    	{
                        	xtype: 'fieldset',
                        	flex: 1,
                        	height: 300,
                        	title: 'Scanned Components',
                        	layout: {
                            	type: 'vbox',
                            	align: 'stretch'
                        	},
                        	items: [
                            	{
                                	xtype: 'gridpanel',
                                	flex: 1,
                                	header: false,
                                	title: 'My Grid Panel',
                                	columns: [
                                    	{
                                        	xtype: 'gridcolumn',
                                        	dataIndex: 'string',
                                        	text: 'String',
                                        	flex: 1
                                    	}
                                	]
                            	}
                        	]
                    	}
                	]
            	}
        	]
    	});

    	me.callParent(arguments);
	}

});
