Ext.define('SMI.view.AppGrid', {
	extend: 'Ext.grid.Panel',
	requires: ['Ext.grid.*'],
	id : 'appgrid',
	alias: 'widget.appgrid',
	scroll: true,
	autoWidth : true,
	autoHeight: true,
	autoScroll: true,
//	layout: 'fit',
	
	initComponent : function(){
		var me = this;
		me.store = Ext.create('SMI.store.appStore');
		me.columns = me.buildColumns();
//	    me.bbar = this.buildBottomBar();
//	    me.selModel = Ext.create('Ext.selection.CheckboxModel',{			
//	    	mode : "SINGLE",
//			listeners:{
//            	selectionchange: me.onSelectionChange
//            }
//        });
	
	    me.callParent(arguments);
	},
	
	
//onSelectionChange : function (sm, selections) {
//		var selected = selections[0];
//		if (selected) {
//		}
//	},

	buildColumns : function() {
		var columns = [{
			header : 'App Alias',
			dataIndex : 'key',
			editor: {
	                allowBlank: false
	        },
			flex : 1
		},
		{
			header : 'File Name',
			dataIndex : 'fileName',
			flex : .75
		},
		{
			header : 'Size (KB)',
			dataIndex : 'size',
			flex : .5
		}];
		return columns;
	}
	});