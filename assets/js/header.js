const profileButton = document.getElementById("profileButton");
const profileDropdown = document.getElementById("profileDropdown");

if(profileButton){

    profileButton.addEventListener("click",(e)=>{

        e.stopPropagation();

        profileDropdown.classList.toggle("active");

    });

    document.addEventListener("click",()=>{

        profileDropdown.classList.remove("active");

    });

}