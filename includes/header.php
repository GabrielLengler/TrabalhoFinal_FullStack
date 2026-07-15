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
            <a href="<?= BASE_URL ?>pages/sobre.php">Sobre</a>

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
                            class="dropdown-item"
                            id="settingsButton"
                            type="button"
                        >
                            <i class="fa-solid fa-gear"></i>
                            Configurações
                        </button>

                        <a
                            href="<?= BASE_URL ?>pages/meus-mundos.php"
                            class="dropdown-item"
                        >
                            <i class="fa-solid fa-earth-americas"></i>
                            Meus Mundos
                        </a>

                        <a
                            href="<?= BASE_URL ?>pages/amigos.php"
                            class="dropdown-item"
                        >
                            <i class="fa-solid fa-user-group"></i>
                            Amigos
                        </a>

                        <div class="dropdown-divider"></div>

                        <button
                            id="logoutButton"
                            class="dropdown-item logout-btn"
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

            <button
                class="language-btn"
                id="languageButton"
                type="button"
                title="Idioma"
            >
                <i class="fa-solid fa-earth-americas"></i>
            </button>

        </div>

    </div>

</header>

<!-- ===========================
     MODAL DE CONFIGURAÇÕES
=========================== -->

<div class="settings-overlay" id="settingsOverlay">

    <div class="settings-modal">

        <button
            class="settings-close"
            id="closeSettings"
            type="button"
        >
            <i class="fa-solid fa-xmark"></i>
        </button>

        <div class="settings-content">

            <!-- Em breve -->

        </div>

    </div>

</div>

<script>
const BASE_URL = "<?= BASE_URL ?>";
</script>

<script src="<?= BASE_URL ?>assets/js/header.js"></script>