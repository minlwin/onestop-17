import * as client from "@/lib/service/client/management/subscription.client"
import { ModificationResult, PageResult, SubscriptionListItem, SubscriptionPageSearch, SubscriptionStatusForm } from "@/lib/types";

export async function search(form : SubscriptionPageSearch) : Promise<PageResult<SubscriptionListItem>> {
    return await client.search(form)
}

export async function updateStatus(id : string, form : SubscriptionStatusForm) : Promise<ModificationResult<string>> {
    return await client.updateStatus(id, form)
}
