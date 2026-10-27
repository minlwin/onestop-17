import { CompanyProfileDto } from "@/lib/types"
import "server-only"
import { secureRequest } from ".."

const BASE_API = "member/company"

export async function findProfile() : Promise<CompanyProfileDto> {
    return await secureRequest(BASE_API)
}