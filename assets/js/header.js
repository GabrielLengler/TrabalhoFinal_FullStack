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