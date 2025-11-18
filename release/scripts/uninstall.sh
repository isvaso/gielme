#!/bin/bash

# Print Gielme logo
cat <<'EOF'

      ██████╗ ██╗███████╗██╗     ███╗   ███╗███████╗
     ██╔════╝ ██║██╔════╝██║     ████╗ ████║██╔════╝
     ██║  ███╗██║█████╗  ██║     ██╔████╔██║█████╗
     ██║   ██║██║██╔══╝  ██║     ██║╚██╔╝██║██╔══╝
     ╚██████╔╝██║███████╗███████╗██║ ╚═╝ ██║███████╗
      ╚═════╝ ╚═╝╚══════╝╚══════╝╚═╝     ╚═╝╚══════╝
       SIMPLE TASK MANAGER                     v0.1

EOF

APP_NAME="gielme"
INSTALL_DIR="/usr/local/lib/${APP_NAME}"
BIN_PATH="/usr/local/bin/${APP_NAME}"
USER_DATA_DIR="${HOME}/Gielme"

if [ "$EUID" -ne 0 ]; then
  echo "Please run this script using sudo:"
  echo "  sudo $0"
  exit 1
fi

echo "This will remove system files:"
echo "  ${INSTALL_DIR}"
echo "  ${BIN_PATH}"
read -p "Remove system files? [y/N] " ANSWER

case "$ANSWER" in
  [yY]|[yY][eE][sS])
    rm -rf "${INSTALL_DIR}"
    rm -f "${BIN_PATH}"
    echo "System files removed"
    ;;
  *)
    echo "Canceled"
    exit 0
    ;;
esac

# Ask about user data
if [ -d "${USER_DATA_DIR}" ]; then
  echo "User data directory detected: ${USER_DATA_DIR}"
  read -p "Remove user data too? [y/N] " ANSWER2

  case "$ANSWER2" in
    [yY]|[yY][eE][sS])
      rm -rf "${USER_DATA_DIR}"
      echo "User data removed"
      ;;
    *)
      echo "User data preserved"
      ;;
  esac
fi

echo "${APP_NAME} uninstalled"
