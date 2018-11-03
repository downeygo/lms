$(function () {
    var URL = "/user";
    var selectURL = "/user_getRole";
    //添加
    $(".add-btn").click(function () {
        //设置不可编辑
        $("#addFrom input[name='username']").prop("readonly", false);
        showAddModal(selectURL);
    });

    //编辑
    $(".edit-btn").click(function () {
        $("#addFrom input[name='username']").prop("readonly", true);
        showUpdateModal(this, URL, "roles", selectURL);
    });

    //保存
    $(".save-btn").click(function () {
        addOrUpdate(URL);
    });

    //删除
    $(".delete-btn").click(function () {
        deleteOne(this, URL);
    });
});