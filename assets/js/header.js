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

            await fetch("http://localhost:8080/user/logout",{

                method:"POST",
                credentials:"include"

            });

        }catch(error){

            console.error(error);

        }

        window.location.href = BASE_URL + "index.php";

    });

}