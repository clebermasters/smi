Ext.define('SMI.model.appModel', {
		extend : 'Ext.data.Model',
		fields : [ {
			name : 'key',
			type : 'string'
		}, {
			name : 'fileName',
			type : 'string'
		}, {
			name : 'version',
			type : 'string'
		}, {
			name : 'link',
			type : 'string'
		}, {
			name : 'extension',
			type : 'string'
		}, {
			name : 'size',
			type : 'long'
		}
		]
	});