$(function(){
    
    //LNB
    $(document).ready(function(e){
        $(".lnb>ul>li>a").click(function(e){
        	var targetUrl = $(this).attr("href");
        	if( targetUrl != "")
        	{
        		top.location.href = targetUrl;
        	}
        	else
        	{
        		if( $(this).siblings().length != 0 )
                	e.preventDefault();
                $(".lnb>ul>li").removeClass('on');
                $(this).parent().addClass('on');
        	}
        }); 
    });
    
	//테이블 하이라이트
	$(function(){
		$('.tbl_list tbody tr').mouseover(function(){
			$(this).children().addClass('selectedRow');
		}).mouseout(function(){
			$(this).children().removeClass('selectedRow');
		});
	});

	//placeholder 처리
	$('input[placeholder]').each(function(){
		var input = $(this);
		$(input).val(input.attr('placeholder'));
		$(input).focus(function(){
			if (input.val() == input.attr('placeholder')) {
				input.val('');
			}
		});
		$(input).blur(function(){
			if (input.val() == '' || input.val() == input.attr('placeholder')) {
				input.val(input.attr('placeholder'));
			}
		});
	});

    //첨부파일 리스트 추가&삭제
    $('.addBtn').click(function(){
        var obj = $(this).parent().prev().children("li:last").clone(true);
        var target = $(this).parent().prev().append(obj);
        target.find("input").last().val("");
        return false;
    });
    
    $('.delBtn').click(function(){
        if($(this).parent().prev().children().length > 1){
            $(this).parent().prev().children("li:last").remove();
        };
        return false;
    });
    
	//달력 일자 선택
	$(".datepicker").datepicker({
		showButtonPanel:true,
		dateFormat:"yy-mm-dd",
		showOn:"button",
        buttonImage:"/resources/admin/images/btn_calendar.png",
		buttonImageOnly:true
	});
    
	$(".datepicker2").datepicker({
		showButtonPanel:true,
		dateFormat:"yy-mm-dd",
		showOn:"button",
        buttonImage:"/resources/admin/images/btn_calendar.png",
		buttonImageOnly:true
	});

});

//공통 사용 modal
var _ModalPopupBackgroundID = 'modal';
function ShowModalPopup(modalPopupID) {
	var popupID = "#" + modalPopupID;
	var popupMarginTop = $(popupID).height() / 2;
	var popupMarginLeft = $(popupID).width() / 2;
	$('body').css('overflow', 'hidden');
	$(popupID).css({
		'left': '50%',
		'top': '50%',
		'margin-top': -popupMarginTop,
		'margin-left': -popupMarginLeft,
		'display': 'block',
		'position': 'fixed',
		'z-index': 99999
	});

	var backgroundSelector = $('<div id="' + _ModalPopupBackgroundID + '" ></div>');
	backgroundSelector.appendTo('body');

	backgroundSelector.css({
		'width': $(document).width(),
		'height': $(document).height(),
		'display': 'none',
		'background-color': '#000',
		'filter':'alpha(opacity=80)',
		'position': 'absolute',
		'top': 0,
		'left': 0,
		'z-index': 99998
	});

	backgroundSelector.fadeTo('fast', 0.8);
		 backgroundSelector.click(function(){
			 HideModalPopup(modalPopupID)
	});

}

function HideModalPopup(modalPopupID) {
	$("#" + modalPopupID).css('display', 'none');
	$("#" + _ModalPopupBackgroundID).remove();
	$('body').css('overflow', 'auto');
}