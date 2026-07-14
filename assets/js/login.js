// ===========================
// MOSTRAR / OCULTAR SENHA
// ===========================

const toggleButtons = document.querySelectorAll(".toggle-password");

toggleButtons.forEach(button => {

    button.addEventListener("click", () => {

        const input = document.getElementById(button.dataset.target);
        const icon = button.querySelector("i");

        if(input.type === "password"){

            input.type = "text";

            icon.classList.remove("fa-eye");
            icon.classList.add("fa-eye-slash");

        }else{

            input.type = "password";

            icon.classList.remove("fa-eye-slash");
            icon.classList.add("fa-eye");

        }

    });

});

// ===========================
// LOGIN
// ===========================

const form = document.querySelector("form");

form.addEventListener("submit", async (e)=>{

    e.preventDefault();

    const usuario = document.getElementById("usuario").value.trim();
    const senha = document.getElementById("password").value;

    if(usuario === "" || senha === ""){

        showToast("Preencha todos os campos.");

        return;

    }

    try{

        const response = await fetch("http://localhost:8080/auth/login",{

            method:"POST",

            credentials: "include",

            headers:{
                "Content-Type":"application/json"
            },

            body: JSON.stringify({

                username: usuario,
                password: senha

            })

        });

        const data = await response.json();

        if(!response.ok){

            switch(data.error){

                case "Invalid username/email or password":
                    showToast("Usuário ou senha incorretos.");
                    break;

                case "Username or email is required":
                    showToast("Informe seu usuário ou e-mail.");
                    break;

                case "Password is required":
                    showToast("Informe sua senha.");
                    break;

                default:
                    showToast(data.error || "Erro ao realizar login.");

            }

            return;

        }

        // Login bem sucedido
        window.location.href = BASE_URL + "index.php";

    }catch(error){

        showToast("Não foi possível conectar ao servidor.");

    }

});