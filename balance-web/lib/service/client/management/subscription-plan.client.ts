import 'server-only'
import { ModificationResult, SubscriptionPlan, SubscriptionPlanForm } from "@/lib/types";
import { secureRequest } from "..";

const BASE_API = "management/master/plans"

export async function findAll() : Promise<SubscriptionPlan[]> {
    return await secureRequest(BASE_API)
}

export async function findById(id : any) : Promise<SubscriptionPlan> {
    return await secureRequest(`${BASE_API}/${id}`)
}

export async function create(form : SubscriptionPlanForm) : Promise<ModificationResult<number>> {
    return await secureRequest(BASE_API, form, {method : "POST"})
}

export async function update(id : any, form : SubscriptionPlanForm) : Promise<ModificationResult<number>> {
    return await secureRequest(`${BASE_API}/${id}`, form, {method : "POST"})
}
