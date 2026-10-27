import { BalanceListItem, BalancePageSearch, BalanceSearchResult } from "@/lib/types";
import "server-only"
import { secureRequest } from "..";

const BASE_API = "member/balance"

export async function search(form: BalancePageSearch) : Promise<BalanceSearchResult> {
    return await secureRequest(BASE_API, form)
}