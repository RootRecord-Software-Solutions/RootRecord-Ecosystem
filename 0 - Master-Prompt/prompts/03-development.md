# Development Workflow

## Standard loop

1. Locate the actual current file.
2. Read the relevant surrounding context.
3. Trace the existing path/flow.
4. Reuse existing operations.
5. Make a surgical edit.
6. Validate syntax and behavior.
7. Independently inspect the result.
8. Hand off with exact paths and evidence.

## Drop-in discipline

Changes should land in the existing final file/path.

Do not create:

- `v2/`
- alternate roots
- renamed copies
- installers
- migration layers

unless the operator explicitly asks for them.

## File layout style (standing)

For operator-edited schedules and catalogs (especially `automations/scripts/jobs.py`):

- Keep `# SECTION:` banners.
- Keep commented **TEMPLATE** blocks at the end of each section.
- Keep **HOW TO ADD A JOB** (or equivalent) in the header.
- Add new live entries *above* the TEMPLATE; never delete the TEMPLATE to “clean up.”
- If the layout was stripped, restore from git history, then re-apply current live jobs.

Canonical example and rules: `prompts/09-file-layout-style.md`.

## Applying code to the live desk — standing format for all future builds

**Keep this deploy format for every future build:**

1. Push to GitHub `main` on the skills repo.
2. Desk `github_sync_all` pulls/merges within about five minutes.
3. A successful skills merge **automatically reloads** the poller stack (full stop → start + window).
4. Do **not** instruct the operator to restart the poller for ordinary code deploys.
5. Do **not** start parallel processes to "activate" the new code.
6. Wire new jobs/features into this path; do not invent a competing restart scheme.

If the operator needs the change immediately, they may reboot the machine or run `/home/rootrecord/rootserver-poller restart` once — optional, not the default AI recommendation.

## Paste-safe execution

When giving commands for the operator to run:

- one paste-safe block per step;
- `set +u +o pipefail` first when using the established relay workflow;
- absolute paths;
- `cat > /absolute/path << 'EOF'` for complete file writes;
- bounded exact-match edits for surgical changes;
- backup before consequential edits;
- verify immediately afterward.

## Validation

A successful file write is not a successful feature.

Test the behavior that changed.

For a live system, verify the resulting state, not just the command's exit status.

## GitHub

Use the repository's existing GitHub structure. Do not create duplicate source copies in the Master Prompt area.

The Master Prompt area should link to frequently touched implementation files instead.

## Live-state context

Read `../state/state.json` from the master-prompt root when operational context is relevant.

Treat the snapshot as context with provenance, not as a substitute for direct verification.
