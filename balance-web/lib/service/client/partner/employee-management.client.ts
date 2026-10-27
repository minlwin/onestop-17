import { EmployeeDetails, EmployeeForm, EmployeeListItem, EmployeeSearch, ModificationResult } from "@/lib/types";
import "server-only"
import { secureRequest } from "..";

const BASE_API = "partner/employee"

export async function search(form: EmployeeSearch) : Promise<EmployeeListItem[]> {
    return await secureRequest(BASE_API, form)
}

export async function findById(id : any) : Promise<EmployeeDetails> {
    return await secureRequest(`${BASE_API}/${id}`)
}

export async function create(form : EmployeeForm) : Promise<ModificationResult<string>> {
    return await secureRequest(BASE_API, form, {method : "POST"})
}