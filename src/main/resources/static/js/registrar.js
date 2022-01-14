
$(document).ready(function() {
	
	//on ready
});

async function registrarUsuario() {
	const request = await fetch('api/usuarios', {
		method: 'POST',
		headers: getHeaders()

	});
	const usuarios = await request.json();

	

}

function getHeaders() {
	return {
		'Accept': 'application/json',
		'Content-Type': 'application/json',
		'Authorization': localStorage.token
	};
}


