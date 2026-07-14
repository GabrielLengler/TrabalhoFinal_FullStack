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
                Entrar na sua Conta
            </h2>

            <p class="login-subtitle">
                Continue administrando seus mundos e mercados.
            </p>

            <form action="" method="POST">

                <div class="form-group">

                    <label for="usuario">
                        E-mail ou Usuário
                    </label>

                    <input
                        type="text"
                        id="usuario"
                        name="usuario"
                        placeholder="Digite seu e-mail ou usuário"
                        required
                    >

                </div>

                <div class="form-group">

                    <label for="password">
                        Senha
                    </label>

                    <div class="password-group">

                        <input
                            type="password"
                            id="password"
                            name="senha"
                            placeholder="Digite sua senha"
                            required
                        >

                        <span
                            class="toggle-password"
                            data-target="password"
                        >
                            <i class="fa-regular fa-eye"></i>
                        </span>

                    </div>

                </div>

                <div class="remember-area">

                    <input
                        type="checkbox"
                        id="remember"
                        name="remember"
                    >

                    <label for="remember">
                        Lembrar-me
                    </label>

                </div>

                <button type="submit" class="login-button">

                    Entrar na Conta

                </button>

            </form>

            <p class="register-text">

                Não possui uma conta?

                <a href="<?= BASE_URL ?>pages/cadastro.php">
                    Cadastre-se gratuitamente
                </a>

            </p>

        </div>

    </section>

</main>

<?php include '../includes/footer.php'; ?>

<script src="<?= BASE_URL ?>assets/js/login.js"></script>

</body>
</html>