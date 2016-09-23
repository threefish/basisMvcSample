/**
 * Created with IntelliJ IDEA.
 * User: 黄川
 * Date Time: 2014/12/2515:35
 */
(function(win,doc){
    /**
     * @param ops.img  [730, 328]
     * @param ops.box  ['scroll_img', 'scroll_position']
     * @param ops.time  defualt 3000 非必填
     * @version 1.0
     */
    var scrollwrap=function(ops){
        /**
         * 图片高宽
         * 0,宽度；1,高度
         * @type {Array}
         * @private
         */
        var _img = ops.img;
        var box= ops.box;
        var time=ops.time ? ops.time:'3000';
        var autoHeight=ops.autoHeight==true ?false:true;
        //视窗大小
        var windowswidth = doc.querySelector("#"+box[0]).offsetWidth;
        var imgheight = _img[1] / (_img[0] / windowswidth);
        console.log("version:1.0,视窗大小:"+ windowswidth);
        var scorllli = $('#'+ box[0]+' li').length;
        for (var i = 0; i < scorllli; i++) {
        	if(autoHeight){
        		$('#' + box[0] + ' li img').attr("style", "width:" + windowswidth + "px;height:" + imgheight + "px;");
        	}else{
        		$('#' + box[0] + ' li img').attr("style", "width:" + windowswidth + "px;");
        	}
        }
        $("#"+box[0]).attr("style", "width:100%;");
        var lihtml = "";
        for (var i = 0; i < scorllli; i++) {
            if (i == 0) {
                lihtml = "<li class='on'></li>";
            } else {
                lihtml += "<li></li>";
            }
        }
        $('#'+box[1]).html(lihtml);
        var slider = Swipe(doc.getElementById(box[0]), {
            auto: time,
            continuous: true,
            callback: function (pos) {
                var i = bullets.length;
                while (i--) {
                    bullets[i].className = ' ';
                }
                bullets[pos].className = 'on';
            }
        });
        var bullets = $('#' + box[1] +' li');
        $('.scroll_position_bg').css({
            width: $('#'+box[1]).width()
        });
    }
    win.scrollwrap= scrollwrap;
}(window,document));