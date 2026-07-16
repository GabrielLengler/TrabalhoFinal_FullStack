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
        formData.append("file", file);

        try {
            const response = await fetch("http://localhost:8080/user/me/profile-picture", {
                method: "PUT",
                credentials: "include",
                body: formData
            });

            if (!response.ok) {
                throw new Error(`Erro no servidor: ${response.status}`);
            }

            const data = await response.json();
            console.log("Resposta do servidor:", data);
            
            const novaUrlFoto = data.profilePicture;

            if (novaUrlFoto) {
                if (settingsProfilePhoto) {
                    settingsProfilePhoto.src = novaUrlFoto;
                }
                if (profileButtonImg) {
                    profileButtonImg.src = novaUrlFoto;
                }
                showToast("Foto de perfil atualizada com sucesso!");
            } else {
                window.location.reload();
            }

        } catch (error) {
            console.error("Erro no upload:", error);
            showToast("Não foi possível atualizar sua foto de perfil.");
        } finally {
            photoInput.value = "";
        }
    });
}
