import { ModificationResult, PaymentInfo, SubscriptionForm, SubscriptionListItem, SubscriptionPlan } from "@/lib/types"
import "server-only"
import { secureRequest } from ".."

const BASE_API = "partner/subscription"

export async function getAll() : Promise<SubscriptionListItem[]> {
    return secureRequest(BASE_API)
}

export async function subscribe(form : SubscriptionForm) : Promise<ModificationResult<string>> {
    return secureRequest(BASE_API, form)
}

export async function getPlans() : Promise<SubscriptionPlan[]> {
    return secureRequest(`${BASE_API}/plans`)
}

export async function getPaymentInfos() : Promise<PaymentInfo[]> {
    return secureRequest(`${BASE_API}/payments`)
}