### Generate key
keytool -genkeypair -alias jwt -keyalg RSA -keypass password -keystore jwt.jks -storepass password

### Convert key jks
keytool -importkeystore -srckeystore jwt.jks -destkeystore jwt.jks -deststoretype pkcs12

### Export public key
keytool -list -rfc --keystore jwt.jks | openssl x509 -inform pem -pubkey

