//下拉選單
$(document).ready(function () {
  $(".collapsed").click(function () {
    $(this).next().toggle("fast");
  });
});
//===============================================================

//ajax跳轉連結註冊
let url = {
		home:{index:"999"},
  ad: {
    Add: "<%=request.getContextPath()%>/back_end/ad/addAd.jsp",
    All: "<%=request.getContextPath()%>/back_end/ad/listAllAd.jsp"
  },
  news: {
    Add: "<%=request.getContextPath()%>/back_end/news/addAd.jsp",
    All: "<%=request.getContextPath()%>/back_end/news/listAllAd.jsp"
  },
};

function doRequest(path) {
  $.ajax({
    url: path,
    type: "GET",
    success: function (data) {
      $(".container-fluid").hide();
      // $("#showDoRequestPage").prepend(data + "<br>");
      $("#showDoRequestPage").html(data);
    },
  });
}
//===============================================================

//跳轉
function goTo(path) {
  $.ajax({
    url: path,
    type: "get",
    success: function (data) {
      $(".container-fluid").hide();
      $("#showDoRequestPage").html(data);
    },
  });
}
//===============================================================

//post請求
function keywordSearch(path) {
  $.ajax({
    url: path,
    type: "post",
    data: { action: "getAll_For_Keyword", keyword: $("#keyword").val() },
    success: function (data) {
      $("#container-fluid").hide();
      $("#showDoRequestPage").html(data);
    }
  });
}
//===============================================================

//ad控制器
$("#adPost").submit(function(e) {

  var form = $(this);
  var url = form.attr('action');

  $.ajax({
         type: "POST",
         url: url,
         contentType: "multipart/form-data",
         data: form.serialize(), // serializes the form's elements.
//         success: function(data){
//          $("#showDoRequestPage").html(data);
//          console.log("99999")
//         }
         success: function(data, textStatus) {
             if (data.redirect) {
                 // data.redirect contains the string URL to redirect to
                 window.location.href = data.redirect;
             } else {
                 // data.form contains the HTML for the replacement form
                 $("#showDoRequestPage").replaceWith(data.form);
             }
         }
       });

  e.preventDefault(); // avoid to execute the actual submit of the form.
});
//===============================================================



