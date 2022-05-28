/* DATABASE */

USE ${ph_dbname};

/* usuario */

CREATE TABLE `usuario` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id do usuario',
  `nome` VARCHAR(255) NOT NULL COMMENT 'nome do usuario',
  `matricula` VARCHAR(36) NOT NULL COMMENT 'matricula do usuario',
  `email` VARCHAR(255) NOT NULL COMMENT 'código (uuid) do usuario',
  `senha` VARCHAR(255) NOT NULL COMMENT 'código (uuid) do usuario',
  `role` VARCHAR(36) NOT NULL COMMENT 'código (uuid) do usuario',
  `dt_created` DATETIME NOT NULL DEFAULT current_timestamp COMMENT 'data de criação da tupla',
  `dt_updated` DATETIME NOT NULL DEFAULT current_timestamp COMMENT 'data da última alteração da tupla',  
  PRIMARY KEY (`id`),
  UNIQUE KEY `UX_usuario_matricula` (`matricula`),
  CONSTRAINT `CK_role` CHECK (`role` IN ('ROLE_ADMIN', 'ROLE_USER'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* account

CREATE TABLE `account` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id da conta',
  `code` VARCHAR(36) NOT NULL COMMENT 'código (uuid) da conta',
  `code_external` VARCHAR(12) COMMENT 'id da conta no sistema parceiro',
  `customer_id` INT UNSIGNED NOT NULL COMMENT 'id do cliente',
  `brcode` VARCHAR(255) COMMENT 'BRCode estático da conta',
  `balance` DECIMAL(12,2) NOT NULL COMMENT 'saldo da conta (multiplicado por 100)',
  `balance_date` DATETIME NOT NULL DEFAULT current_timestamp COMMENT 'data em que o saldo da conta foi atualizado',
  `status` VARCHAR(50) NOT NULL COMMENT 'status da conta',
  `dt_created` DATETIME NOT NULL DEFAULT current_timestamp COMMENT 'data de criação da tupla',
  `dt_updated` DATETIME NOT NULL DEFAULT current_timestamp COMMENT 'data da última alteração da tupla',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UX_account_code` (`code`),
  UNIQUE KEY `UX_account_customer_id` (`customer_id`),
  UNIQUE KEY `UX_brcode` (`brcode`),
  CONSTRAINT `FK_account_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `CK_status` CHECK (`status` IN ('PRE_ACCOUNT', 'SIMPLE_PENDING', 'SIMPLE' ,'KYC_PENDING', 'KYC_APPROVED'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* customer_image */

CREATE TABLE `customer_image` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id da imagem',
  `customer_id` INT UNSIGNED NOT NULL COMMENT 'código (uuid) do cliente desta imagem',
  `code_external` VARCHAR(30) NOT NULL COMMENT 'id da imagem no sistema parceiro',
  `image_type` VARCHAR(50) NOT NULL COMMENT 'tipo da imagem',
  `dt_created` DATETIME NOT NULL DEFAULT current_timestamp COMMENT 'data de criação da tupla',
  `dt_updated` DATETIME NOT NULL DEFAULT current_timestamp COMMENT 'data da última alteração da tupla',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UX_code_external` (`code_external`),
  KEY `IX_photo_customer_id` (`customer_id`),
  CONSTRAINT `FK_photo_customer_id` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`),
  CONSTRAINT `CK_image_type` CHECK (`image_type` IN ('RG_FRONT', 'RG_BACK', 'RG_SELFIE', 'CNH_FRONT', 'CNH_BACK', 'CNH_SELFIE'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* transaction */

CREATE TABLE `transaction` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id da transação',
  `code` VARCHAR(36) NOT NULL COMMENT 'código (uuid) da transação',
  `code_external` VARCHAR(15) NOT NULL COMMENT 'id da transacao no sistema parceiro',
  `account_id` INT UNSIGNED NOT NULL COMMENT 'id da conta desta transação',
  `amount` DECIMAL(12,2) NOT NULL COMMENT 'valor da transação',
  `description` VARCHAR(100) NOT NULL COMMENT 'comentários relacionados a esta transação',
  `date` DATE NOT NULL COMMENT 'data na qual esta transação é listada dentre os lançamentos diários.',
  `datetime` DATETIME NOT NULL COMMENT 'timestamp relativo ao momento em que esta transação foi registrada. Considere o retorno sempre em conformidade com a ISO-8601 e com offset zero (UTC)',
  `dt_created` DATETIME NOT NULL DEFAULT current_timestamp COMMENT 'data de criação da tupla',
  `dt_updated` DATETIME NOT NULL DEFAULT current_timestamp COMMENT 'data da última alteração da tupla',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UX_transaction_code` (`code`),
  UNIQUE KEY `UX_transaction_code_external` (`code_external`),
  KEY `IX_transaction_account_id` (`account_id`),
  KEY `IX_transaction_account_id_date` (`account_id`, `date`),
  CONSTRAINT `FK_transaction_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/* transfer */

CREATE TABLE `transfer` (
  `id` INT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT 'id da transferência',
  `type` VARCHAR(20) NOT NULL DEFAULT 'PIX' COMMENT 'tipo de transferência',
  `code` VARCHAR(36) NOT NULL COMMENT 'código (uuid) da transferência',
  `account_id` INT UNSIGNED NOT NULL COMMENT 'id da conta desta transferência',
  `amount` INTEGER UNSIGNED NOT NULL COMMENT 'valor da transferência',
  `pix_end_to_end_id` VARCHAR(50) COMMENT 'id retornado ao decodificar uma chave PIX',
  `target_transaction_key` VARCHAR(50) NOT NULL COMMENT 'chave pix de destino',
  `comments` VARCHAR(100) COMMENT 'comentários relacionados a esta transferência',
  `status` VARCHAR(50) NOT NULL COMMENT 'Status da transferência',
  `code_external` VARCHAR(50) COMMENT 'código da transação obtida pelo parceiro wallet',
  `return_external` TEXT COMMENT 'retorno da transação obtida pelo parceiro wallet',
  `date` DATE NOT NULL COMMENT 'data na qual esta transferência é listada dentre os lançamentos diários.',
  `datetime` DATETIME NOT NULL COMMENT 'timestamp relativo ao momento em que esta transferência foi registrada. Considere o retorno sempre em conformidade com a ISO-8601 e com offset zero (UTC)',

  `dt_created` DATETIME NOT NULL DEFAULT current_timestamp COMMENT 'data de criação da tupla',
  `dt_updated` DATETIME NOT NULL DEFAULT current_timestamp COMMENT 'data da última alteração da tupla',
  PRIMARY KEY (`id`),
  UNIQUE KEY `UX_transfer_code` (`code`),
  KEY `IX_transfer_account_id` (`account_id`),
  CONSTRAINT `FK_transfer_account_id` FOREIGN KEY (`account_id`) REFERENCES `account` (`id`),
  CONSTRAINT `CK_transfer_type` CHECK (`type` IN ('PIX')),
  CONSTRAINT `CK_status` CHECK (`status` IN ('EM_PROCESSAMENTO', 'FINALIZADO', 'ERRO'))
) ENGINE=InnoDB DEFAULT CHARSET=utf8; */
