$(document).ready(function(){

	getNote();
});
	
	
	function getNote(){
		$("#note_tittle").attr("disabled",true);
		$("#note_detail").attr("disabled",true);
		$("#updateBtn").html("Güncelle");
		
	$.ajax({
		type:"POST",
		url:'./../getNote',
		contentType:'text/plain',
		data:$("#id").val()+"",
		success:function(data){
         $("#note_tittle").val(data.tittle);
         $("#note_detail").val(data.content);
			
		},error:function(data){
			alert(data);
		}
	});
	
	
	}
	
	var updatem=false;
	function update()
	{
		if(!updatem){
		$("#note_tittle").attr("disabled",false);
		$("#note_detail").attr("disabled",false);
		$("#updateBtn").html("Kaydet");
		updatem=true;
		}else{
		updateNote();
		updatem=false;
		}
	}
	
	function updateNote() {
		var param={
				
				id:$("#id").val(),
				tittle:$("#note_tittle").val(),
				content:$("#note_detail").val()
		}
		var ser_data=JSON.stringify(param);
		$.ajax({	
			type:"POST",
			contentType:'application/json; charset=UTF-8',
			url:"./../updateNote",
			data: ser_data,
			success:function(data){
				alert(data);
				getNote();
			},error:function(data){
				alert(data);
			}
		});
	}	
	
	
	function deleteNote() {
		var param={
				
				id:$("#id").val()
				
		}
		var ser_data=JSON.stringify(param);
		$.ajax({	
			type:"POST",
			contentType:'application/json; charset=UTF-8',
			url:"./../deleteNote",
			data: ser_data,
			success:function(data){
				alert(data);
			window.history.back();
			},error:function(data){
				alert(data);
			}
		});
	}	

