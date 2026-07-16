const profileButton = document.getElementById("profileButton");
const profileDropdown = document.getElementById("profileDropdown");
const logoutButton = document.getElementById("logoutButton");

if(profileButton){

    profileButton.addEventListener("click",(e)=>{

        e.stopPropagation();

        profileDropdown.classList.toggle("active");

    });

    document.addEventListener("click",()=>{

        profileDropdown.classList.remove("active");

    });

}

if(logoutButton){

    logoutButton.addEventListener("click", async ()=>{

        try{

            await fetch("http://localhost:8080/auth/logout",{

                method:"POST",
                credentials:"include"

            });

        }catch(error){

            console.error(error);

        }

        window.location.href = BASE_URL + "index.php";

    });

}

// ===========================
// CONFIGURAÇÕES
// ===========================

const settingsButton = document.getElementById("settingsButton");
const settingsOverlay = document.getElementById("settingsOverlay");
const closeSettings = document.getElementById("closeSettings");

if(settingsButton){

    settingsButton.addEventListener("click",()=>{

        profileDropdown.classList.remove("active");

        settingsOverlay.classList.add("active");

    });

}

if(closeSettings){

    closeSettings.addEventListener("click",()=>{

        settingsOverlay.classList.remove("active");

    });

}

if(settingsOverlay){

    settingsOverlay.addEventListener("click",(e)=>{

        if(e.target === settingsOverlay){

            settingsOverlay.classList.remove("active");

        }

    });

}

// ===========================
// ALTERAR FOTO DE PERFIL
// ===========================

const changePhotoButton = document.getElementById("changePhotoButton");
const photoInput = document.getElementById("photoInput");
const settingsProfilePhoto = document.getElementById("settingsProfilePhoto");
const profileButtonImg = document.getElementById("profileButton");

if (changePhotoButton && photoInput) {
    changePhotoButton.addEventListener("click", () => {
        photoInput.click();
    });

    photoInput.addEventListener("change", async () => {
        const file = photoInput.files[0];

        if (!file) return;

        const formData = new FormData();
        formData.append("file", file); // Garanta que o nome "file" é o mesmo do @RequestParam no Java

        try {
            // AJUSTE AQUI: Altere a URL abaixo para a rota exata definida no seu Controller Java!
            // Exemplo: "http://localhost:8080/usuarios/profile-picture" ou "http://localhost:8080/api/usuarios/profile-picture"
            const response = await fetch("http://localhost:8080/usuarios/profile-picture", {
                method: "POST", // Se o seu Java usar @PutMapping, altere aqui para "PUT"
                credentials: "include", // Envia os cookies do JWT configurados nas suas properties
                body: formData
            });

            if (!response.ok) {
                throw new Error(`Erro no servidor: ${response.status}`);
            }

            const data = await response.json();
            console.log("Resposta do servidor:", data);
            
            // Tenta obter a URL da foto de todas as formas possíveis que o Java costuma retornar
            const novaUrlFoto = data.url || 
                                data.profilePicture || 
                                data.profile_picture || 
                                (data.usuario && (data.usuario.profilePicture || data.usuario.profile_picture));

            if (novaUrlFoto) {
                if (settingsProfilePhoto) {
                    settingsProfilePhoto.src = novaUrlFoto;
                }
                if (profileButtonImg) {
                    profileButtonImg.src = novaUrlFoto;
                }
                alert("Foto de perfil atualizada com sucesso!");
            } else {
                // Se salvou com sucesso no banco, mas não retornou a URL de forma direta no JSON, 
                // recarrega a página para o PHP renderizar o caminho atualizado do banco.
                window.location.reload();
            }

        } catch (error) {
            console.error("Erro no upload:", error);
            alert("Não foi possível atualizar sua foto de perfil. Verifique se o servidor Spring Boot está rodando na porta 8080 e se a rota está correta.");
        } finally {
            photoInput.value = "";
        }
    });
}