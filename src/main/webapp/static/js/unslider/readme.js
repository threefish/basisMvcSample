//调用方式
$(function() {
	if (window.chrome) {
		$('.banner li').css('background-size', '100% 100%');
	}
	$('.banner').unslider({
		arrows: true,
		dots: true
	});
	//处理点击页面a标签 href=#不滚动页面
	$('a[href^="#"]').click(function() {
		var target = $($(this).attr('href'));
		var pos = target.offset();
		if (pos) {
			$('html, body').animate({
				scrollTop: pos.top,
				scrollLeft: pos.left
			}, 1000);
		}
		return false;
	});
});

//HTML
//<div class="banner">
//							<ul>
//								<li style="background-image: url('img/1.jpg');">
//									<div class="inner">
//										<h1>The jQuery slider that just slides.</h1>
//										<p>就是这个不到3kb的插件！没有奇特的特效或无用的标签。</p>
//										<a class="btn" href="#download">下载</a>
//									</div>
//								</li>
//								<li style="background-image: url('img/2.jpg');">
//									<div class="inner">
//										<h1>Fluid, flexible, fantastically minimal.</h1>
//										<p>Use any HTML in your slides, extend with CSS. You have full control.</p>
//										<a class="btn" href="#download">下载</a>
//									</div>
//								</li>
//							</ul>
//						</div>