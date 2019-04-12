//解析文章数据
function build_article_table(result) {
    
}
//解析用户数据
function build_user_table(result) {
    
}
//解析评论数据
function build_comments_table(result) {
    $("#paging_table tbody").empty();
    var comments = result.extend.pageInfo.list;
    $.each(comments,function (index,item) {
        
    })
}