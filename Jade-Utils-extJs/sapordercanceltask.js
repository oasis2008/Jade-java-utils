
/**
 * 主要对象MODEL
 * @class com.arvato.oms.sephora.sapordercanceltask
 * @extends Ext.data.Model
 */
var orderBasicStatusStore = Ext.create('Ext.data.Store', {
    fields: ['label', 'value'],
    proxy: {
        type: 'ajax',
        url: path('/systemParamVal/query.json?defParam=BASIC_STATUS'),
        reader: {
            type: 'json',
            root: 'result'
        }
    },
    autoLoad: true
});
var getSapOrderCancelStatusStore = Ext.create('Ext.data.Store', {
    fields: ['label', 'value'],
    proxy: {
        type: 'ajax',
        url: path('/systemParamVal/query.json?defParam=SAP_ORDERCANCELTASK_STATUS'),
        reader: {
            type: 'json',
            root: 'result'
        }
    },
    autoLoad: true
});


Ext.define('OmsSapOrderCancelTaskModel', {
	extend:'Ext.data.Model',
	fields:[
		{name:'taskId', type:'string'}
		,{name:'omsOrderNo', type:'string'}
		,{name:'basicStatus', type:'string'}
		,{name:'cancelReason', type:'string'}
		,{name:'createTime', type:'date',dateFormat:'time'}
		,{name:'csrOperaterId', type:'string'}
		,{name:'omsOrderSysId', type:'string'}
		,{name:'operaterId', type:'string'}
		,{name:'remark', type:'string'}
		,{name:'taskStatus', type:'string'}
		,{name:'updateTime', type:'date',dateFormat:'time'}
			  
	],
	proxy:{
		type:'ajax',
		url:path('/sapOrderCancelTask/jsonList.do'),
		extraParams:{
//	   	sort : "[{\"property\":\"createTime\",\"direction\":\"DESC\"}]"
//			order:'createTime', direction:'DESC'
//			order:'createTime'
		},
		reader:{
			type:'json',
			root:'result',
			idProperty:'id',
			totalProperty:'recordCount',
			successProperty:'success'
		}
	}

});

/**
 * 表单窗口
 * @class com.arvato.oms.sephora.sapordercanceltask
 * @extends Ext.Window
 */
Ext.define('com.arvato.oms.sephora.sapordercanceltaskFormWin', {
	alias:'widget.SapOrderCancelTaskFormWin',
	extend:'Ext.Window',
	modal:true,
	layout:'fit',
	resizable:false,
	constructor:function (config) {
		var me = this;
		me.items = Ext.create('com.arvato.oms.sephora.sapordercanceltaskForm', Ext.apply({}, config));
		me.callParent();
	}
});

/**
 * 表单Form
 * @class com.arvato.oms.sephora.sapordercanceltask
 * @extends Oms.form.Panel
 */
Ext.define('com.arvato.oms.sephora.sapordercanceltaskForm',{
	alias:'widget.SapOrderCancelTaskForm',
	extend:'Smartec.form.Panel',
	tab:true,
	id:'SapOrderCancelTaskForm',
	layout:'fit',
	constructor:function (config) {
		var me = this;
		Ext.apply(me, config);
		me.callParent();
	},

	initComponent:function () {
		var me = this;
		var baseTab = me.getBaseTab();
		me.items = [baseTab];
		me.setButton();
		me.callParent();
		me.loadRecord();
	},
	getTitle:function(){
		return messages['compile'];
	},
	getBaseTab:function () {
		var me = this;
		return {
			title:me.getTitle(),
			layout:'column',
			height:380,
			defaults:{
				labelWidth:60,
				margin:'0 15 10 15',
				columnWidth:.5
			},
		   items: [
				{xtype:'hiddenfield',name:'taskId',allowBlank:false, hidden:true}
				,{xtype: 'textfield',name:'omsOrderSysId',fieldLabel:'OmsOrderSysId',allowBlank:false}
				,{xtype: 'textfield',name:'omsOrderNo',fieldLabel:'OmsOrderNo',allowBlank:false}
				,{xtype: 'textfield',name:'taskStatus',fieldLabel:'TaskStatus',allowBlank:false}
				,{xtype: 'textfield',name:'basicStatus',fieldLabel:'BasicStatus',allowBlank:false}
				,{xtype: 'textfield',name:'operaterId',fieldLabel:'OperaterId',allowBlank:false}
				,{xtype: 'textfield',name:'csrOperaterId',fieldLabel:'CsrOperaterId',allowBlank:false}
				,{xtype: 'textfield',name:'cancelReason',fieldLabel:'CancelReason',allowBlank:false}
				,{xtype: 'textfield',name:'remark',fieldLabel:'Remark',allowBlank:false}
				,{xtype: 'datefield',name:'createTime',maxValue: new Date(),fieldLabel:'CreateTime',allowBlank:false}
				,{xtype: 'datefield',name:'updateTime',maxValue: new Date(),fieldLabel:'UpdateTime',allowBlank:false}
			 
			  ]
		};
	},
	//新增
	saveHandle:function () {
		var me = this;
		var formPanel = me.findParentByType('form');
		var form = formPanel.getForm();

		if (form.isValid()) {
			form.submit({
				url:path('/sapOrderCancelTask/jsonUpdate.do'),
				success:function (form, action) {
					if (action.result.success) {
						Ext.Msg.alert(messages['common.title.tips'], messages['common.success.save'], function (btn) {
							formPanel.store.load();
							me.up('window').close();
						});
					} else {
						Ext.Msg.alert(messages['common.title.tips'],  messages['common.error.save']);
					}
				},
				failure:function (form, action) {
					Ext.Msg.alert(messages['common.title.tips'], messages['common.error.sys']);
				}
			});
		}
	},
	//编辑
	updateHandle:function () {
		var me = this;
		var formPanel = me.findParentByType('form');
		var form = formPanel.getForm();
		if (form.isValid()) {
			form.submit({
				url:path('/sapOrderCancelTask/jsonUpdate.do'),
				success:function (form, action) {
					if (action.result.success) {
						Ext.Msg.alert(messages['common.title.tips'], messages['common.success.save'], function (btn) {
							formPanel.store.load();
							me.up('window').close();
						});
					} else {
						Ext.Msg.alert(messages['common.title.tips'], messages['common.error.save']);
					}
				},
				failure:function (form, action) {
					Ext.Msg.alert(messages['common.title.tips'], messages['common.error.sys']);
				}
			});
		}
	},
	setButton:function () {
		var me = this;
		me.buttons = [
			{
				text:messages['common.action.commit'],
				itemId:'addBtnSubmit',
				hidden:me.editMode,
				handler:me.saveHandle
			},
			{
				text:messages['common.action.commit'],
				itemId:'updateBtnSubmit',
				hidden:!(me.editMode),
				handler:me.updateHandle
			},
			{
				text:messages['common.action.close'],
				margin:'0 0 0 4px',
				handler:function () {
					me.up('window').close();
				}
			}
		];
	},

	loadRecord:function () {
		var me = this;
		var form = me.getForm();
		if (me.record) {
			form.loadRecord(me.record);
		}

	}

});

/**
 * 主表格入口
 * @class com.arvato.oms.sephora.sapordercanceltask
 * @extends Ext.grid.GridPanel
 */
Ext.define('com.arvato.oms.sephora.sapordercanceltask.grid', {
	extend:'Smartec.grid.Panel',
	alias:'widget.SapOrderCancelTaskGrid',
	title:messages['com.arvato.oms.sephora.sapordercanceltask.grid.title'],
    selModel:Ext.create('Ext.selection.CheckboxModel', {
        injectCheckbox:1,
        headerWidth:80,
//        width:30,
        checkOnly:false
    }),
	forceFit:true,
	autoScroll:true,
	model:OmsSapOrderCancelTaskModel,
	getCurrentId:function () {
		var me = this;
		var records = me.getSelectionModel().getSelection();
		if (records.length > 0) {
			var record = records[0];
			var pkId='taskId';
			return record.get(pkId);
		} else {
			return "0";
		}
	},
	getCurrentRecord:function () {
		var me = this;
		var records = me.getSelectionModel().getSelection();
		if (records.length > 0) {
			var record = records[0];
			return record;
		} else {
			return me.getSelectionModel().getLastSelected();
		}
	},

    columns:[
        {xtype: 'rownumberer', text: messages['common.table.seq'], width: 15, sortable: false}
		,{header:messages['sapOrderCancel.taskId'],       hidden:true, width:100,sortable:true,dataIndex:'taskId'}
		,{header:messages['sapOrderCancel.OmsOrderSysId'],hidden:true, width:100,sortable:true,dataIndex:'omsOrderSysId'}
		,{header:messages['sapOrderCancel.OmsOrderNo'],   hidden:false,width:100,sortable:true,dataIndex:'omsOrderNo'}
		,{header:messages['sapOrderCancel.BasicStatus'],  hidden:false,width:100,sortable:true,dataIndex:'basicStatus',
            renderer:function (value, p, record) {
                if (value != '') {
                    var record = orderBasicStatusStore.findRecord('value', value);
                    if (record) {
                        return record.get('label');
                    } else {
                        return value;
                    }
                } else {
                    return value;
                }
            }
        }
		,{header:messages['sapOrderCancel.TaskStatus'],width:100,sortable:true,dataIndex:'taskStatus',
            renderer:function (value, p, record) {
                if (value != '') {
                    var record = getSapOrderCancelStatusStore.findRecord('value', value);
                    if (record) {
                        return record.get('label');
                    } else {
                        return value;
                    }
                } else {
                    return value;
                }
            }
        }
		,{header:messages['sapOrderCancel.OperaterId'],hidden:true,width:100,sortable:true,dataIndex:'operaterId'}
		,{header:messages['sapOrderCancel.CsrOperaterId'],hidden:true,width:100,sortable:true,dataIndex:'csrOperaterId'}
		,{header:messages['sapOrderCancel.CancelReason'],width:100,sortable:true,dataIndex:'cancelReason'}
		,{header:messages['sapOrderCancel.Remark'],width:100,sortable:true,dataIndex:'remark'}
		,{header:messages['common.table.CreateTime'],width:100,sortable:true,dataIndex:'createTime', renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
		,{header:messages['common.table.UpdateTime'],hidden:true,width:100,sortable:true,dataIndex:'updateTime', renderer:Ext.util.Format.dateRenderer('Y-m-d H:i:s')}
	],
	//过滤器设置
	filterItems: [
		{type:'string', dataIndex:'omsOrderNo', operate:'like'}
		,{type: 'list', dataIndex:'basicStatus', phpMode: true, options: [['ACTIVE', '活动'], ['LOCKED', '锁定'], ['FINISH','结束']]}
		,{type: 'list', dataIndex:'taskStatus', phpMode: true, options: [['WAITTING', '等待处理'], ['CANCELED', '取消'], ['REJECTED','拒绝']]}
		,{type: 'datetime',dataIndex:'createTime', date:{format:'Y-m-d'}, time:{format:'H:i:s', increment: 1}}
	],
   
	initComponent:function () {
		var me = this;

		me.topDockedItems = [
			{
				xtype:'button',
				itemId:'btnCancel',
				disabled:true,
				height:24,
				text: messages['sapOrderCancel.rejectBtn'],
				iconCls:'icon-delete',
				scope:this,
				handler:me.rejectCancelRequest
			}
		];
		me.callParent();
		me.getSelectionModel().on('selectionchange', function (thiz, selections) {
			var isDisable = true;
			if(selections.length > 0){
				isDisable = false;
				for(var i=0; i< selections.length; i++){
					var rec = selections[i];
					if('FINISH'==rec.get('basicStatus') 
							&& 'REJECTED'==rec.get('taskStatus')) 
					{
						isDisable = true; break;
					}
				}
			}
			me.down('#btnCancel').setDisabled(isDisable);
		});
	},
	//右键菜单
	onMessageContextMenu : function (grid, rowIndex, e) {
		e.stopEvent();
		var coords = e.getXY();
		var record = grid.getStore().getAt(rowIndex);
		var messageContextMenu = new Ext.menu.Menu({
			id: 'messageContextMenu',
			items: [{icon:'../../images/edit.gif',text: messages['common.action.edit'],handler: rgtEdit,scope: this},
					{id: 'delete',icon:'../../images/delete.gif',handler: rgtDelete,text: messages['common.action.delete']
			}]
		});
		//右键编辑
		function rgtEdit() {
			messageContextMenu.hide();
			var grid = this.up('gridpanel');
			if(grid){
				grid.editHandle();
			}
									
		};
		//右键删除
		function rgtDelete() {
			messageContextMenu.hide();
			if (!record||record.length == 0) {
				Ext.Msg.alert(messages['common.title.tips'], messages['sapOrderCancel.selectForDelete']);
				return;
			}
			var grid = this.up('gridpanel');
			if(grid){
				grid.deleteHandle();
			}
		};
		messageContextMenu.showAt([coords[0], coords[1]]);
		e.preventDefault();//to disable the standard browser context menu
	}
	
	//双击事件
	,dblclick :function(){
			var sm = this.getSelectionModel();
	   		var record=null;
			try{
				record=sm.getSelected();
				if(record==null){
					return;
				}
			}
			catch(e){
				try{
					record=sm.selection.record();
				}
				catch(ex){}
			}
			this.showWinForm(record);
	},
	addHandle:function () {//新建数据

		//var grid = this.up('gridpanel');
		var me = this;
		var store = me.getStore();
		var record, editMode;
		editMode = false;
		//定义窗口
		var win = Ext.create('com.arvato.oms.sephora.sapordercanceltaskFormWin', {
			editMode:editMode,
			store:store,
			height:300,
			width:500
		});
		win.setTitle(messages['common.action.create']);
		win.show(this);
	},
	editHandle:function () {//编辑数据
		//var grid = this.up('gridpanel');
		var me = this;
		var store = me.getStore();
		var sm = this.getSelectionModel();
		var record, editMode;
		// 编辑
		if (sm.hasSelection()) {
			record = sm.getSelection()[0];
		}
		editMode = true;
		var win = Ext.create('com.arvato.oms.sephora.sapordercanceltaskFormWin', {
			editMode:editMode,
			record:record,
			store:store,
			height:300,
			width:500
		});
		win.setTitle(messages['common.action.edit']);
		win.show(this);
	},
	deleteHandle:function () {//删除数据
		var me = this;
		//var grid = me.up('gridpanel');
		var sm = me.getSelectionModel();
		var pkId='taskId';

		if (sm.hasSelection()) {
			Ext.Msg.confirm(messages['common.title.tips'], messages['Ext.MessageBox.delete.confirmMsg'], function (btn) {
				if (btn == 'yes') {

					var rows = sm.getSelection();
					var ids = "";
					for (var i = 0; i < rows.length; i++) {
						ids += rows[i].get(pkId);
						if (i < rows.length - 1) {
							ids += ",";
						}
					}
					Ext.Ajax.request({
						url:path('/sapOrderCancelTask/delete.do'),
						params:{
							ids:ids
						},
						success:function (json) {
							var data = Ext.decode(json.responseText);
							if (data.success) {
								Ext.Msg.alert(messages['common.title.tips'], messages['Ext.MessageBox.delete.successfulMsg'], function (btn) {
									gride.getStore().load();
								});
							} else {
								Ext.Msg.alert(messages['common.title.tips'], messages['Ext.MessageBox.delete.failureMsg']);
								gride.getStore().load();
							}
						},
						failure:function () {
							Ext.Msg.alert(messages['common.title.tips'], messages['Ext.MessageBox.system.errorMsg']);
						}
					});
				}

			});
		}
	},
	rejectCancelRequest : function () {//驳回撤单申请
		var me = this;
		var sm = me.getSelectionModel();
		var pkId='taskId';

		if (sm.hasSelection()) {
			Ext.Msg.confirm(messages['common.title.tips'], messages['sapOrderCancel.confirmReject'], function (btn) {
				if (btn == 'yes') {
					var rows = sm.getSelection();
					var ids = "";
					for (var i = 0; i < rows.length; i++) {
						ids += rows[i].get(pkId);
						if (i < rows.length - 1) {
							ids += ",";
						}
					}
					Ext.Ajax.request({
						url:path('/sapOrderCancelTask/rejectCancel.do'),
						params:{
							ids:ids
						},
						success:function (json) {
							var data = Ext.decode(json.responseText);
							if (data.success) {
								Ext.Msg.alert(messages['common.title.tips'], messages['sapOrderCancel.rejectSuccess']);
								me.getStore().load();
							} else {
								Ext.Msg.alert(messages['common.title.tips'], messages['sapOrderCancel.rejectFail']);
								me.getStore().load();
							}
						},
						failure:function () {
							Ext.Msg.alert(messages['common.title.tips'], messages['Ext.MessageBox.system.errorMsg']);
						}
					});
				}

			});
		}
	}
});

Ext.onReady(function () {
	var centerPanel = Ext.getCmp('center');
	
	var mainTabPanel = Ext.create('Ext.tab.Panel', {
		id:'mainTab',
		activeTab:0,
		autoHeight:true,
		items:[
			{
				title: messages['sapOrderCancel.OmsOrderSysId'],
				layout:'fit',
				items:{xtype:'SapOrderCancelTaskGrid'}
			}
		]
	});
	centerPanel.add(mainTabPanel);
});
 
