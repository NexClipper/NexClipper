/*
 * Creado por ebc erick benites erickpm.
 * Generador de sprites.png: http://preloaders.net/en/popular (escoger APNG, transparent, 64x64 o nxn, download as sprite y solo considerar el png) (Usa IE para Generador)
 */
;(function($) {

	// Get script path
	var scriptPath = document.getElementsByTagName('script')[document.getElementsByTagName('script').length - 1].src;
	var scriptFolder = scriptPath.substr(0, scriptPath.lastIndexOf( '/' )+1 );

	$.fn.preloader = function(options) {

		if(options == 'stop'){
			clearTimeout($(this).data('interval'));
    		$(this).hide();
    		return;
		}

		// Merge passed options with defaults
		var opts = $.extend({}, $.fn.preloader.defaults, options);

		return this.each(function() {
			// Merge in the metadata elements for this specific node
			var o = $.metadata ? $.extend({}, opts, $.metadata.get(this)) : opts;
			_preloaderStart(o, $(this));
		});
	};

	// Preloader plugin default options
	$.fn.preloader.defaults = {
		speed : 100,	// Miliseconds between frames (ms)
        src : scriptFolder + 'img/sprites1.png',
        modal : false,
        className : false,

        width : false,
        height : false,
        frames : false
	};

	// Define our base to add to
	$.preloader = {};

	// Static Function
	$.preloader.start = function(options) {
		// Merge passed options with defaults
		var opts = $.extend({}, $.fn.preloader.defaults, options);

		_preloaderStart(opts);
	}

	// Static Function
	$.preloader.stop = function() {

		_preloaderStop();

	}


	// Your privates functions code ...

    function _preloaderStart(opts, e) {

    	clearTimeout($(e).data('interval'));

    	if(!e){

    		if(opts.modal){
    			$('<div/>').addClass('jquery-preloader-overlay').css({
					position: 'fixed',
					top: '0',
					left: '0',
					height: '100%',
					width: '100%',
					backgroundColor: 'rgba(0,0,0,0.5)',
					display: 'none',
					'z-index': '9998'
    			}).appendTo(document.body).fadeIn('slow'); //.click(function(){_preloaderStop()});
    		}

    		e = $('<div/>').addClass('jquery-preloader').css({
					position: 'fixed',
				    top: '50%',
				    left: '50%',
					'z-index': '9999'
				}).appendTo(document.body);

    		if(opts.position == 'top-left')
    			e.css({top: '0%', left: '0%'});
    		else if(opts.position == 'top-right')
    			e.css({top: '0%', left: '100%'});
    		else if(opts.position == 'bottom-left')
    			e.css({top: '100%', left: '0%'});
    		else if(opts.position == 'bottom-right')
    			e.css({top: '100%', left: '100%'});
    		else if(opts.position && (opts.position.top || opts.position.left)){
    			if(opts.position.top || opts.position.left)
    				e.css({top: opts.position.top});
    			if(opts.position.top || opts.position.left)
    				e.css({left: opts.position.left});
    		}

    		if(opts.className) e.addClass(opts.className);
    	}

    	if(opts.src.indexOf('/') == -1) opts.src = scriptFolder + 'img/' + opts.src;

		genImage = new Image();
		genImage.src=opts.src;

		genImage.onload=function (){

			if(!opts.frames) opts.frames = this.width / this.height;
			if(!opts.height) opts.height = this.height;
			if(!opts.width) opts.width = this.width / opts.frames;

			if(!$(e).is(":visible")) $(e).show();

			$(e).css('background-image', 'url('+opts.src+')');

			$(e).css('width', opts.width+'px');
			$(e).css('height', opts.height+'px');

			if($(e).hasClass('jquery-preloader')){
				$(e).css('marginLeft', -opts.width/2+'px');
				$(e).css('marginTop', -opts.height/2+'px');

				if(opts.position == 'top-left')
	    			e.css({marginLeft: '10px', marginTop: '10px'});
	    		else if(opts.position == 'top-right')
	    			e.css({marginLeft: (-opts.width - 10) + 'px', marginTop: '10px'});
	    		else if(opts.position == 'bottom-left')
	    			e.css({marginLeft: '10px', marginTop: (-opts.height - 10) + 'px'});
	    		else if(opts.position == 'bottom-right')
	    			e.css({marginLeft: (-opts.width - 10) + 'px', marginTop: (-opts.height - 10) + 'px'});
			}

			var interval = setInterval(function(){

				var xpos = ($(e).data('xpos'))?$(e).data('xpos'):0;	// Get xpos from element's metadata

				xpos += parseInt(opts.width);	// Slide position
				//console.log(e.css('left') + '-'+$(e).data('xpos'));

				if(xpos/opts.width >= opts.frames) xpos = 0;	// Reset position

				$(e).css('background-position', -xpos + 'px 0');	// Style execute

				$(e).data('xpos', xpos);	// Set xpos to element's metadata

			}, opts.speed);

			$(e).data('interval', interval);

		};

		genImage.onerror=function(){
			if(console) console.log('No se pudo cargar el preloader');
		};

		return e;

    }

    function _preloaderStop(){

    	$('div.jquery-preloader').each(function(){
    		clearTimeout($(this).data('interval'));
    		$(this).remove();
    	});
    	$('div.jquery-preloader-overlay').remove();

    }

})(jQuery);

$(function(){
	// How to use:
	//
	// var e = $('#loader').preloader({src:'sprites.png', frames:8, width:64, height:64, speed:9, className: 'mycss'}); // src: (in js/jquery/preloaders/img path)
	// var e = $('#loader').preloader('stop');
	//
	// $.preloader.start({modal: true, position:'top-right'}); // position: [(default:'center') | 'top-left' | 'top-right' | ' bottom-left' | 'bottom-right' | {top:'200px', left:'200px'}]
	// $.preloader.stop();
	//
	// var e = $('<div/>').css('margin', '0 auto').insertAfter($('#conteiner')).preloader(); // Preloader center into a 'container'
	// e.preloader('stop');
});