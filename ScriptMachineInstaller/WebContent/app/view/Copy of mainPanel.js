Ext.define('SMI.view.mainPanel', {
extend : 'Ext.panel.Panel',
alias : 'widget.mainPanel',
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
    		this.callParent(arguments);
    	},
        
        
        buildPanel : function() {
    		var me = this;
    	    var mainPanel = [{
                region: 'north',
                collapsible: true,
                title: 'North',
                split: true,
                height: '30%',
                minHeight: 60,
                html: 'north'
            },{
                region: 'west',
                collapsible: true,
//                layout: 'absolute',
                title: 'Starts at width 30%',
                split: true,
                width: '50%',
                minWidth: 100,
                minHeight: 140,
                bodyPadding: 10,
                stateId: 'westRegion',
//                stateful: true,
                items: []
            },{
                region: 'center',
                html: 'center center',
                title: 'Center',
                minHeight: 80,
                items: [],
            },{
                region: 'south',
                collapsible: true,
                split: true,
                height: '30%',
                minHeight: 120,
                title: 'South',
                layout: {
                    type: 'vbox',
                    align: 'stretch'
                },
//                flex : 1,
                items: [{xtype:'panel',
                	  layout: {
                          type: 'hbox',
                          align: 'stretch'
                      },
                      flex : 1,
                      items : [{xtype:'appgrid',
                    	  		flex: 1,
                      
                      }]
                }]
            }];
    	    
    	    return mainPanel;
    	},
        
        
        
    });


