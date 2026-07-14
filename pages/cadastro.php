<?php
require_once '../config.php';
?>

<!DOCTYPE html>
<html lang="pt-BR">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>WMRPG</title>

    <link rel="icon" type="image/png" href="<?= BASE_URL ?>assets/images/wm-logo.png">

    <link rel="stylesheet" href="<?= BASE_URL ?>assets/css/variable.css">
    <link rel="stylesheet" href="<?= BASE_URL ?>assets/css/global.css">
    <link rel="stylesheet" href="<?= BASE_URL ?>assets/css/header.css">
    <link rel="stylesheet" href="<?= BASE_URL ?>assets/css/footer.css">
    <link rel="stylesheet" href="<?= BASE_URL ?>assets/css/login.css">

    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css">
    
</head>
<body>

<?php include '../includes/header.php'; ?>

<main>

    <section class="login-section">

        <div class="login-card">

            <h2 class="login-title">
                Criar uma Conta
            </h2>

            <p class="login-subtitle">
                Comece agora a criar e administrar seus mundos de RPG.
            </p>

            <form action="" method="POST">

                <div class="form-group">

                    <label for="email">
                        E-mail
                        <span class="required">*</span>
                    </label>

                    <input
                        type="email"
                        id="email"
                        name="email"
                        placeholder="Digite seu e-mail"
                        required
                    >

                </div>

                <div class="form-group">

                    <label for="usuario">
                        Nome de Usuário
                        <span class="required">*</span>
                    </label>

                    <input
                        type="text"
                        id="usuario"
                        name="usuario"
                        placeholder="Escolha um nome de usuário"
                        required
                    >

                </div>

                <div class="form-group">

                    <label for="password">
                        Senha
                        <span class="required">*</span>
                    </label>

                    <div class="password-group">

                        <input
                            type="password"
                            id="password"
                            name="senha"
                            placeholder="Digite sua senha"
                            required
                        >

                        <span class="toggle-password" data-target="password">
                            <i class="fa-regular fa-eye"></i>
                        </span>

                    </div>

                </div>

                <div class="form-group">

                    <label for="confirmPassword">
                        Confirmar Senha
                        <span class="required">*</span>
                    </label>

                    <div class="password-group">

                        <input
                            type="password"
                            id="confirmPassword"
                            name="confirmarSenha"
                            placeholder="Digite novamente sua senha"
                            required
                        >

                        <span class="toggle-password" data-target="confirmPassword">
                            <i class="fa-regular fa-eye"></i>
                        </span>

                    </div>

                </div>

                <div class="remember-area">

                    <input
                        type="checkbox"
                        id="notificacoes"
                        name="notificacoes"
                    >

                    <label for="notificacoes">
                        Desejo receber novidades e atualizações por e-mail.
                    </label>

                </div>

                <button type="submit" class="login-button">

                    Criar Conta

                </button>

            </form>

            <p class="register-text">

                Já possui uma conta?

                <a href="<?= BASE_URL ?>pages/login.php">
                    Entrar
                </a>

            </p>

        </div>

    </section>

</main>

<div id="toast"></div>

<?php include '../includes/footer.php'; ?>

<script>
const BASE_URL = "<?= BASE_URL ?>";
</script>

<script src="<?= BASE_URL ?>assets/js/header.js"></script>

<script src="<?= BASE_URL ?>assets/js/utils.js"></script>

<script src="<?= BASE_URL ?>assets/js/cadastro.js"></script>

</body>
</html>