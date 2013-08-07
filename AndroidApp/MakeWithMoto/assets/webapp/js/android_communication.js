
//listing apps in the future we might filter the listing
function list_apps(filter) { 
	var obj = {};
	obj.cmd = "list_apps";
	obj.filter = filter;

	$.get("cmd="+JSON.stringify(obj), function(data) {
		setProjectList(filter, JSON.parse(data));
	});
}

//push the code
function push_code(project) { 
	var obj = {};
	obj.cmd = "push_code";

	var o = new Object(); 
	o.name = project.name;
	o.url = project.url;
	o.code = project.code;
	o.type = project.type;

	$.ajax({
		url:"cmd="+JSON.stringify(obj),
		type: 'post',
		data: o,
		success: function(data) {
			
		}
	});

}

//fetch the code
function fetch_code(pName, pType) { 
	var obj = {};
	obj.cmd = "fetch_code";
	obj.name = pName;
	obj.type = pType;
	$.get("cmd="+JSON.stringify(obj), function(data) {
		var code = JSON.parse(data);
		setCode(unescape(code.code));
		currentProject.name = pName;
		currentProject.type = pType;
	});
}

function run_app(project) {
	var obj = {};
	obj.cmd = "run_app";
	obj.name = project.name;
	obj.url = project.url;
	obj.type = project.type;
	$.get("cmd="+JSON.stringify(obj), function(data) {
 		//alert('Load was performed. ' + data);
	});
}

function create_new_app(id) {
	var obj = {};
	obj.cmd = "create_new_app";
	obj.remove_app = id;
	$.get("cmd="+JSON.stringify(obj), function(data) {
 		alert('Load was performed. ' + data);
	});
}
function remove_app(id) {
	var obj = {};
	obj.cmd = "remove_app";

	obj.remove_app = id;
	$.get("cmd="+JSON.stringify(obj), function(data) {
 		alert('Load was performed. ' + data);
	});
}

function get_documentation(id) {
	var obj = {};
	obj.cmd = "get_documentation";

	$.get("cmd="+JSON.stringify(obj), function(data) { 
		var doc = JSON.parse(data);
		parse_help(doc.api);
	});
}


function get_camera() {
	var url = 'http://'+localhost+':8080/takePic';

	$.get(url, function(data) { 
		
		var img = $("<img />").attr('src', 'http://somedomain.com/image.jpg').load(function() {
        if (!this.complete || typeof this.naturalWidth == "undefined" || this.naturalWidth == 0) {
            alert('broken image!');
        } else {
            $("#widgets").append(img);
        }
    });

	});
}



//OK fetch_code("ioio_1");
//OK list_apps();
//OK run_app("ioio_1");
//OK push_code("ioio_1", "lala");
//NO create_new_app("mm");
//NO remove_app("ioio_1");
//NO get_help();
