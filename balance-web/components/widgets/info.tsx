import { Item, ItemContent, ItemDescription, ItemTitle } from "../ui/item";

export default function Info({label, value} : {label : string, value : string}) {
    return (
        <Item variant={'muted'}>
            <ItemContent>
                <ItemTitle>{label}</ItemTitle>
                <ItemDescription>{value}</ItemDescription>
            </ItemContent>
        </Item>
    )
}