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
// ASTERISCOS
// ===========================

const inputs = document.querySelectorAll(".form-group input");

inputs.forEach(input => {

    const required = input
        .closest(".form-group")
        .querySelector(".required");

    function verificarCampo(){

        if(input.value.trim() !== ""){

            required.style.display = "none";

        }else{

            required.style.display = "inline";

        }

    }

    input.addEventListener("input", verificarCampo);

    verificarCampo();

});

const form = document.querySelector("form");

form.addEventListener("submit", async (e)=>{

    e.preventDefault();

    const email = document.getElementById("email").value.trim();
    const usuario = document.getElementById("usuario").value.trim();
    const senha = document.getElementById("password").value;
    const confirmar = document.getElementById("confirmPassword").value;
    const notificacao = document.getElementById("notificacoes").checked;

    if(!email || !usuario || !senha || !confirmar){

        showToast("Preencha todos os campos.");

        return;

    }

    if(senha !== confirmar){

        showToast("As senhas não coincidem.");

        return;

    }

    try{

        const response = await fetch("http://localhost:8080/auth/register",{

            method:"POST",

            headers:{
                "Content-Type":"application/json"
            },

            body: JSON.stringify({

                username: usuario,

                email: email,

                password: senha,

                notification: notificacao

            })

        });

        const data = await response.json();

        if(!response.ok){

            switch(data.error){

                case "Username already exists":
                    showToast("Este nome de usuário já está em uso.");
                    break;

                case "Email already exists":
                    showToast("Este e-mail já está cadastrado.");
                    break;

                case "Username is required":
                    showToast("Informe um nome de usuário.");
                    break;

                case "Email is required":
                    showToast("Informe um e-mail.");
                    break;

                case "Email is invalid":
                    showToast("Digite um e-mail válido.");
                    break;

                case "Password is required":
                    showToast("Informe uma senha.");
                    break;

                case "Password must have between 8 and 120 characters":
                    showToast("A senha deve possuir entre 8 e 120 caracteres.");
                    break;

                default:
                    showToast(data.error || "Erro ao criar conta.");

            }

            return;

        }

        window.location.href = BASE_URL + "index.php?success=register";

    }catch(error){

        showToast("Não foi possível conectar ao servidor.");

    }

});