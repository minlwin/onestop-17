import { PartnerState } from "@/lib/types";
import "server-only"
import { secureRequest } from "..";

const BASE_API = "partner/state"

export async function getState() : Promise<PartnerState> {
    return await secureRequest(BASE_API)
}