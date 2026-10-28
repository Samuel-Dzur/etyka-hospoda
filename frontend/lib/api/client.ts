import createClient from "openapi-fetch";
import type { paths } from "./schema";

const apiOrigin = (process.env.NEXT_PUBLIC_API_URL ?? "http://localhost:8081").replace(/\/$/, "");

export const api = createClient<paths>({
  baseUrl: `${apiOrigin}/api`,
});
