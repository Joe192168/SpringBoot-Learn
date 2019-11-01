/**
 * Created by CDHong on 2018/4/7.
 */
layui.use(["form","layer","jquery","table"],function(){
    var form = layui.form,
        layer = layui.layer,
        $ = layui.jquery,
        table = layui.table;

    //设置全局默认参数
    table.set({
        elem:"#userList",
        url:"user/list",
        method:"post",
        //even:true,  //隔行变色
        limits:[10,15,20],
        height: 'full-150',
        page:{ limit:10}, //开启分页，并设置每页显示的条数
        cols:[[
            {checkbox: true},
            {field: 'id', title: '用户编号', width:180, sort: true, align: 'center'},
            {field: 'username', title: '用户名称', sort: true},
            {field: 'password', title: '用户密码'},
            {fixed: 'right',title: '操作', width:150, align:'center', toolbar: '#user-tool'}
        ]],
        text:{
            none:'没有查询到符合条件的数据'
        }
    });

    //初始化表格数据
    var rootTable = table.render();

    //查询
    form.on("submit(search-btn)",function(data){
        table.render({
            where:data.field
        });
        return false;
    });

    //编辑和修改通用弹出层
    function layerOpen(title,param){
        layer.open({
            type:2,
            title:title,
            content:"user/add-edit/"+param,
            skin:"layui-layer-molv",
            area: ['600px', '300px'],
            anim: 1,
            cancel: function(){
                table.render();
            }
        });
    }
    //添加用户弹出层
    $(".add-btn").click(function(){
        layerOpen("添加部门",-1);
    });

    //删除，添加和编辑的通用方法
    function sendAjax(url,info){
        $.ajax({
            url:url,
            method:"post",
            data:info,
            success:function(){
                layer.msg("执行成功！");
                //var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                //parent.layer.close(index); //再执行关闭
            },
            error:function(){
                layer.msg("执行失败！");
            }
        });
    }
    //添加用户
    form.on('submit(add-submit)',function(data){
        sendAjax("user/add-edit",data.field);
        return false;
    });

    //修改用户
    form.on("submit(edit-submit)",function(data){
        sendAjax("user/add-edit",data.field);
        return false;

    });

    //监听工具条
    table.on('tool(user-list)',function(obj){
        var lay_event = obj.event; //获得 lay-event 对应的值
        var data = obj.data;  //获得当前行数据
        //var tr = obj.tr; //获得当前行 tr 的DOM对象
        if(lay_event == 'edit'){
            layerOpen("编辑用户",data.id);
        }else if(lay_event == 'del'){
           layer.confirm('你确定要删除【'+data.username+'】用户吗？',{icon:3,title:"删除提示",skin:"layui-layer-molv"},function(index){
               sendAjax("user/del/"+data.id);
               layer.close(index);
               table.render();
            });
        }
    });

});
