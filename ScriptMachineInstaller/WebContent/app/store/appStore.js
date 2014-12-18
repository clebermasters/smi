Ext.define('SMI.store.appStore', {
		extend : 'Ext.data.Store',
		model     : 'SMI.model.appModel',
	    storeId   : 'appStore',
		initComponent : function(){
	    	this.callParent();
	    	console.log('Store iniciada');
	    },
		proxy : {
			type : 'ajax',
			async: false,
			//url: 'listaExt',
			api : {
				create : 'adicionaApp',
				read : 'getlistfiles',
				update : 'alteraApp',
				destroy : 'removeApp',
			},

			reader : Ext.create('Ext.data.JsonReader', {
				type : 'json',
				root : 'data',
			}),

			writer : Ext.create('Ext.data.JsonWriter', {
				type : 'json', 
				//root: 'contatos',
				writeAllFields : true,
				encode : false,

			})
		},
		autoLoad : true,
		autoSync : false,
	});