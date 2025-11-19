#!/bin/bash

# Print Gielme logo
cat <<'EOF'

      ██████╗ ██╗███████╗██╗     ███╗   ███╗███████╗
     ██╔════╝ ██║██╔════╝██║     ████╗ ████║██╔════╝
     ██║  ███╗██║█████╗  ██║     ██╔████╔██║█████╗
     ██║   ██║██║██╔══╝  ██║     ██║╚██╔╝██║██╔══╝
     ╚██████╔╝██║███████╗███████╗██║ ╚═╝ ██║███████╗
      ╚═════╝ ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝
       SIMPLE TASK MANAGER                     v1.1

EOF

APP_NAME="gielme"
JAR_NAME="gielme.jar"
INSTALL_DIR="/usr/local/lib/${APP_NAME}"
BIN_PATH="/usr/local/bin/${APP_NAME}"

# Root required
if [ "$EUID" -ne 0 ]; then
  echo "Please run this script using sudo:"
  echo "  sudo $0"
  exit 1
fi

# Java 17+ required
if ! command -v java >/dev/null 2>&1; then
  echo "Error: Java not found. Please install Java 17+"
  exit 1
fi

JAVA_VERSION_RAW="$(java -version 2>&1 | head -n 1)"
JAVA_VERSION_NUM=$(java -version 2>&1 | awk -F[\".] '/version/ {print $2}')

if [ -z "$JAVA_VERSION_NUM" ]; then
  echo "Error: unable to determine Java version"
  echo "Detected: $JAVA_VERSION_RAW"
  exit 1
fi

if [ "$JAVA_VERSION_NUM" -lt 17 ]; then
  echo "Error: Java version 17+ is required"
  echo "Your version: $JAVA_VERSION_RAW"
  exit 1
fi

JAVA_CMD="$(command -v java)"

SCRIPT_DIR="$(cd "$(dirname "$0")" && pwd)"

if [ ! -f "${SCRIPT_DIR}/${JAR_NAME}" ]; then
  echo "Error: ${JAR_NAME} not found"
  echo "Expected path: ${SCRIPT_DIR}/${JAR_NAME}"
  exit 1
fi

echo "Installing ${APP_NAME}..."

# Check existing installation
if [ -d "${INSTALL_DIR}" ] || [ -f "${BIN_PATH}" ]; then
  echo "Existing installation detected:"
  [ -d "${INSTALL_DIR}" ] && echo "  ${INSTALL_DIR}"
  [ -f "${BIN_PATH}" ] && echo "  ${BIN_PATH}"
  read -r -p "Reinstall (overwrite system files)? [y/N] " ANSWER
  case "$ANSWER" in
    [yY]|[yY][eE][sS])
      echo "Reinstalling..."
      rm -rf "${INSTALL_DIR}"
      rm -f "${BIN_PATH}"
      ;;
    *)
      echo "Installation canceled"
      exit 0
      ;;
  esac
fi

# Install JAR
mkdir -p "${INSTALL_DIR}"
cp "${SCRIPT_DIR}/${JAR_NAME}" "${INSTALL_DIR}/"

# Create launcher script
cat <<EOF > "${BIN_PATH}"
#!/bin/bash

# Check Java presence
if ! command -v java >/dev/null 2>&1; then
  echo "Error: Java not found. Please install Java 17+"
  exit 1
fi

# Check Java version
JAVA_VERSION_RAW=\$(java -version 2>&1 | head -n 1)
JAVA_VERSION_NUM=\$(java -version 2>&1 | awk -F[\".] '/version/ {print \$2}')

if [ -z "\$JAVA_VERSION_NUM" ]; then
  echo "Error: unable to determine Java version"
  echo "Detected: \$JAVA_VERSION_RAW"
  exit 1
fi

if [ "\$JAVA_VERSION_NUM" -lt 17 ]; then
  echo "Error: Java version 17+ is required"
  echo "Your version: \$JAVA_VERSION_RAW"
  exit 1
fi

exec "${JAVA_CMD}" -jar "${INSTALL_DIR}/${JAR_NAME}" "\$@"
EOF

chmod +x "${BIN_PATH}"

echo "${APP_NAME} installed successfully"
echo "Run: ${APP_NAME}"

