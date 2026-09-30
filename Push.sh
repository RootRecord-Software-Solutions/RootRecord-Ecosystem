#!/bin/bash

ROOT="/home/rootrecord/RootRecord-Ecosystem"

LOG_DIR="/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/Logs/Github/Manual"
mkdir -p "$LOG_DIR"

LOG="$LOG_DIR/push-$(date +%Y%m%d-%H%M%S).log"

REPOS=(
"1 - Servers/1 - RootRecord-Pacific-Solar-Server"
"2 - RootRecord-Database"
"5 - RootRecord-Library"
)

MESSAGE=$(zenity --entry \
 --title="RootRecord Ecosystem Commit" \
 --text="Commit message:" \
 --width=500)

if [ -z "$MESSAGE" ]; then
    exit 0
fi

exec > >(tee -a "$LOG") 2>&1

echo "========================================"
echo " RootRecord Ecosystem Push"
echo "========================================"
echo "Time: $(date)"
echo "Message: $MESSAGE"
echo "Log: $LOG"
echo

for repo in "${REPOS[@]}"; do

    PATH_TO_REPO="$ROOT/$repo"

    echo "========================================"
    echo "$repo"
    echo "========================================"

    if [ ! -d "$PATH_TO_REPO/.git" ]; then
        echo "ERROR: Not a git repository"
        echo
        continue
    fi

    echo "--- Status ---"
    git -C "$PATH_TO_REPO" status --short

    echo "--- Add ---"
    git -C "$PATH_TO_REPO" add .

    echo "--- Commit ---"

    if git -C "$PATH_TO_REPO" commit -m "$MESSAGE"; then

        echo "--- Push ---"

        if git -C "$PATH_TO_REPO" push; then
            echo "STATUS: PUSH OK"
        else
            echo "STATUS: PUSH FAILED"
        fi

    else

        echo "STATUS: NOTHING TO COMMIT"

    fi

    echo

done

echo "========================================"
echo " COMPLETE"
echo "========================================"
echo "Saved:"
echo "$LOG"

read -p "Press ENTER to close..."
