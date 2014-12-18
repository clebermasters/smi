Ext.define('SMI.view.mainPanel', {
extend : 'Ext.panel.Panel',
alias : 'widget.mainpanel',
id : 'mainpanel',
requires : ['SMI.view.AppGrid'],
        layout: {
            type: 'border',
            padding: 1
        },
        defaults: {
            split: true
        },

        initComponent : function() {
    		var me = this;
    		me.title = 'Script Machine Installer',
//    		me.border = 0;
    		me.items = me.buildPanel();
    		me.dockedItems = me.buildDockedItems();
    		this.callParent(arguments);
    	},
        
        buildPanel : function() {
    		var me = this;
    		var cellEditing = Ext.create('Ext.grid.plugin.CellEditing', {
                clicksToEdit: 2
            });
    		var mainPanel = [{
    			region: 'north',
//    			collapsible: true,
//    			title: 'North',
    			header: false,
//    			split: true,
    			bodyPadding: 15,
    			height: '18%',
    			minHeight: 30,
    			items: [{xtype:'form',
    				id : 'northForm',
    				layout: {
    					type: 'hbox'
    				},
    				border: 0,
//    				padding: '5 5 5 5',
    				width: '100%',
    				items:[{xtype:'panel',
    					layout: {
    						type: 'hbox',
    						algin:'stretch'
    					},
    					border: 0,
    					width: '50%',
    					items:[{xtype:'panel',
    						layout: {
    							type: 'vbox'
    						},
    						border: 0,
    						width: '100%',
    						items: [{xtype:'combobox',
    							fieldLabel:'App',
    							id : 'appcombobox',
    							labelWidth: 50,
    							store : Ext.create('SMI.store.appStore'),
    							queryMode: 'local',
    							displayField: 'key',
    							allowBlank: false,
    							valueField: 'key',
    							width: '100%',
    							flex: 1,
    						},{xtype:'panel',
    							layout: {
    								type: 'hbox',
    								align:'stretch'
    							},
    							border: 0,
    							width: '100%',
    							style : {
    								'margin-left' : '60px'  
    							},
    							items:[{xtype:'checkbox',
    								fieldLabel:'64 bits',
    								labelWidth:50
    							},{xtype:'checkbox',
    								fieldLabel:'Apt get',
    								labelWidth:50,
    								style : {
    									'margin-left' : '15px'  
    								}},
    								]}]}]
    				},{xtype:'panel',
    					layout: {
    						type: 'hbox'
    					},
    					style : {
    						'margin-left' : '20px'  
    					},
    					border: 0,
    					width: '100%',
    					items:[{xtype:'panel',
    						layout: {
    							type: 'hbox'
    						},
    						border: 0,
    						width: '100%',
    						items:[{xtype:'textfield',
    							fieldLabel: 'URL',
    							id: 'url',
    							value: 'polar.com/e_manuals/FT4/Polar_FT4_user_manual_English/manual.pdf',
    							labelWidth: 50,
    							width: '40%',
    						},{xtype: 'button',
    							text: 'Send to Server',
    							id: 'btnSentToServer',
    							action:'btnSentToServer',
    							margin : '0 0 0 10',
    							width: '8%',
    						}]
    					}]
    				}]
    			},{xtype:'progressbar',
    			   id: 'downloadStatus',
    			   margin: '10 0 0 0',
    			   value: 0,
    			   flex: 1,
    			}]
    		},{
    			region: 'west',
    			collapsible: true,
    			split: true,
//    			collapse: function (direction, animate) {
//    				debugger;
//    		        var me = this;
//    		        me.callParent();
//    		        me.fireEvent('aftercollapse', me);
//    		    },
    			title: 'Script App Editor',
    			layout: {
    				type: 'hbox',
    				align: 'stretch'
    			},
    			width: '50%',
    			minWidth: 100,
    			minHeight: 140,
    			bodyPadding: 10,
    			stateId: 'westRegion',
//  			stateful: true,
    			items: [{xtype:'panel',
    				layout: {
    					type: 'vbox',
    					align: 'stretch'
    				},
    				border: 0,
    				flex : 1,
    				items : [{xtype:'textarea',
    	    			id : 'scripteditor',
    	    			blockLoad : '',

    					flex: 1
    				},{xtype:'fieldset',
    				   title: 'Script App',
    				   items: [{xtype:'panel',
    					   		layout : {
    					   			type : 'hbox',
    					   			pack : 'center',
    					   			align : 'middle',
    					   			
    					   		},
    					   		border: 0,
    					   		items: [{xtype:'button',
    					   				text: 'Save',
    					   				action: 'onSaveAppScript',
    					   				margin: '0 15 0 0',
    					   				flex: 1,
    					   		},{	xtype:'button',
    					   			text: 'Reload',
    					   			flex: 1,
    					   			margin: '0 15 0 0',
    					   		},{xtype:'button',
					   				text: 'Load Block',
					   				action: 'onLoadBlockWindow',
					   				margin: '0 15 0 0',
					   				flex: 1,
    					   		}]
    					   		
    					   }]
    				}]
    			}]
    		},{
    			region: 'center',
    			collapseDirection: 'right',
    			collapsible: true,
    			width: '50%',
    			split: true,
//    			collapse: function (direction, animate) {
//    		        var me = this;
//    		        me.callParent();
//    		        me.fireEvent('aftercollapse', me);
//    		    },
    			title: 'Script Installer View',
    			layout: {
    				type: 'hbox',
    				align: 'stretch'
    			},
//    			flex: 1,
    			bodyPadding: 10,
    			items: [{xtype:'panel',
    				layout: {
    					type: 'vbox',
    					align: 'stretch'
    				},
    				border: 0,
    				flex : 1,
    				items : [{xtype:'textarea',
    						  id : 'scriptInstaller',
    						  cls: '',
    						  flex: 1
    				},{xtype:'fieldset',
    				   title: 'Download Script',
    				   items: [{xtype:'panel',
    					   		layout : {
    					   			type : 'hbox'
    					   			
    					   		},
    					   		border: 0,
    					   		items: [{xtype:'button',
    					   				text: 'Script Installer',
    					   				flex: 1,
    					   				margin: '0 15 0 0',
    					   		},{		xtype:'button',
    					   				text: 'Script Downloader',
    					   				flex: 1,	
    					   		}]
    					   		
    					   }]
    				}]
    			}],
    		},{
    			region: 'south',
    			collapsible: true,
    			split: true,
    			height: '30%',
    			minHeight: 120,
    			title: 'Application List',
    			layout: {
    				type: 'vbox',
    				align: 'stretch'
    			},
//  			flex : 1,
    			items: [{xtype:'panel',
    				layout: {
    					type: 'hbox',
    					align: 'stretch'
    				},
    				flex : 1,
    				items : [{xtype:'appgrid',
    						  id:'appgrid',
    						  plugins: [cellEditing],
    					flex: 1,

    				}]
    			}]
    		}];

    		return mainPanel;
        },
    	
    	buildDockedItems : function(){
    		items = {	        
                xtype: 'toolbar',
                dock: 'bottom',
                items: [
    	        { 	action: 'onDeleteApp',
    	        	text: 'Delete App'
    	        }]
            };
            return items;       
    	}
    });


