import { APP_RELEASE, APP_VERSION } from "../lib/constants";

export function AppFooter() {
  return (
    <p className="footer-credit">
      Root Goals {APP_VERSION} · Release {APP_RELEASE} ·{" "}
      <a href="https://www.rootrecord.cloud" target="_blank" rel="noopener noreferrer">
        Root Record
      </a>
      {" · "}
      <a href="https://www.rootrecord.cloud/terms" target="_blank" rel="noopener noreferrer">
        Terms
      </a>
      {" · "}
      <a href="https://www.rootrecord.cloud/privacy" target="_blank" rel="noopener noreferrer">
        Privacy
      </a>
    </p>
  );
}
