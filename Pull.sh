#!/bin/bash

ROOT="/home/rootrecord/RootRecord-Ecosystem"

LOG_DIR="/home/rootrecord/RootRecord-Ecosystem/2 - RootRecord-Database/Logs/Github/Manual"
mkdir -p "$LOG_DIR"

LOG="$LOG_DIR/pull-$(date +%Y%m%d-%H%M%S).log"

REPOS=(
"1 - Servers/1 - RootRecord-Pacific-Solar-Server"
"2 - RootRecord-Database"
"5 - RootRecord-Library"
)

exec > >(tee -a "$LOG") 2>&1

echo "========================================"
echo " RootRecord Ecosystem Smart Pull"
echo "========================================"
echo "Time: $(date)"
echo "Log: $LOG"
echo

for repo in "${REPOS[@]}"; do

PATH_TO_REPO="$ROOT/$repo"

echo "========================================"
echo "$repo"
echo "========================================"

if [ ! -d "$PATH_TO_REPO/.git" ]; then
    echo "ERROR: Missing git repository"
    continue
fi


cd "$PATH_TO_REPO" || continue


echo "--- Fetch ---"
git fetch origin


echo "--- Ensure upstream ---"

if ! git rev-parse --abbrev-ref --symbolic-full-name @{u} >/dev/null 2>&1; then
    echo "Setting upstream origin/main"
    git branch --set-upstream-to=origin/main main
fi


LOCAL=$(git rev-parse main)
REMOTE=$(git rev-parse origin/main)
BASE=$(git merge-base main origin/main)


echo "LOCAL : $LOCAL"
echo "REMOTE: $REMOTE"
echo "BASE  : $BASE"


if [ "$LOCAL" = "$REMOTE" ]; then

    echo "STATUS: Already synchronized"


elif [ "$LOCAL" = "$BASE" ]; then

    echo "STATUS: Behind remote"
    git pull --ff-only


elif [ "$REMOTE" = "$BASE" ]; then

    echo "STATUS: Local commits ahead"
    echo "ACTION REQUIRED: push pending"

else

    echo "STATUS: Diverged"

    BACKUP="backup/manual-pull-$(date +%Y%m%d-%H%M%S)"

    echo "Creating safety branch:"
    git branch "$BACKUP"

    echo "Attempting merge:"
    git merge origin/main --no-edit

    if [ $? -ne 0 ]; then
        echo
        echo "================================"
        echo "MERGE CONFLICT"
        echo "Manual resolution required"
        echo "Backup branch:"
        echo "$BACKUP"
        echo "================================"
        git status
    else
        echo "Merge successful"
    fi

fi

echo

done


echo "========================================"
echo " COMPLETE"
echo "========================================"

echo "Saved:"
echo "$LOG"

read -p "Press ENTER to close..."
