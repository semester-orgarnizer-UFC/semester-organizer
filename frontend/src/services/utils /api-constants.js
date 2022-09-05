import { getToken } from "../local-storage"

export const API_URL = "http://localhost:8080"
export const AUTHORIZATION_HEADERS = "Authorization: Bearer " + getToken();