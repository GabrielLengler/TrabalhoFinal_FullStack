<?php

if (!isset($usuarioLogado)) {
    $usuarioLogado = false;
}

?>

<header>

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">

    <div class="header-container">

        <a href="<?= BASE_URL ?>index.php" class="logo-link">

            <div class="logo-area">

                <img
                    src="<?= BASE_URL ?>assets/images/wm-logo.png"
                    alt="Logo"
                    class="logo"
                >

                <h1>WorldMarketRPG</h1>

            </div>

        </a>

        <nav class="navbar">

            <a href="<?= BASE_URL ?>index.php">Início</a>
            <a href="<?= BASE_URL ?>pages/mundos.php">Mundos</a>
            <a href="<?= BASE_URL ?>pages/criacao.php">Criação</a>

        </nav>

        <div class="user-area">

            <?php if($usuarioLogado): ?>

                <div class="profile-menu">

                    <img
                        src="<?= BASE_URL ?>assets/images/default-profile.png"
                        class="profile-photo"
                        id="profileButton"
                        alt="Foto de Perfil"
                    >

                    <div class="profile-dropdown" id="profileDropdown">

                        <button
                            id="logoutButton"
                            class="logout-btn"
                            type="button"
                        >
                            <i class="fa-solid fa-right-from-bracket"></i>
                            Sair
                        </button>

                    </div>

                </div>

            <?php else: ?>

                <a href="<?= BASE_URL ?>pages/login.php" class="login-btn">
                    Entrar / Cadastrar
                </a>

            <?php endif; ?>

        </div>

    </div>

</header>

<script>
const BASE_URL = "<?= BASE_URL ?>";
</script>

<script src="<?= BASE_URL ?>assets/js/header.js"></script>